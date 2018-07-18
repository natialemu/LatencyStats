package domain.builder;

import dal.LatencyDAO;
import dal.LatencyDaoImpl;
import domain.MethodBean;
import domain.abstraction.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AstBuilder {
    private LatencyDAO latencyDAO;
    private ServiceAST root;

    public AstBuilder(String applicationName){
        latencyDAO = new LatencyDaoImpl();
        root = new ApplicationAbs(applicationName);

    }


    public  ServiceAST getService(String applicationName, String requestID){

        List<MethodBean> methodBeanList=latencyDAO.getOrderedMethods(applicationName,requestID);

        for(MethodBean method: methodBeanList){
            String fullMethodDefinition = method.getMethodAbs().getName();
            ServiceComponentBuilder builder = new ServiceComponentBuilder(fullMethodDefinition);
            List<ServiceAST> components = builder.getComponents();
            insertIntoApplication(components);
        }

        ServiceAST serviceAST=null;

        /**
         * Use DAOs to get the neccessary data to build AST
         */
        try {
            File file =
                    new File("");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){

                String[] currentLine = sc.nextLine().split(",");
                assert(currentLine.length == 3);

                String[] methodSignature = currentLine[0].split(".");


                if(serviceAST == null){
                    serviceAST = new ApplicationAbs();
                    serviceAST.setName(applicationName);
                }
                PackageAbs packageAbs = parsePackage(methodSignature,serviceAST);
                ClassAbs classAbs = parseClass(packageAbs.getName(),packageAbs,methodSignature[methodSignature.length-2],serviceAST);
                MethodAbs methodAbs = parseMethod(classAbs.getName(),classAbs,methodSignature[methodSignature.length-1],serviceAST,Integer.parseInt(currentLine[2]));
            }
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        return serviceAST;
    }

    private void insertIntoApplication(List<ServiceAST> components) {
        //TODO
    }

    private static ClassAbs parseClass(String currentPackageName, ServiceAST parent, String simpleclassName, ServiceAST serviceAST){

        String className = currentPackageName + "." + simpleclassName;
        ClassAbs classAbs = new ClassAbs();
        classAbs.setName(className);
        if(!serviceAST.contains(classAbs)){
            serviceAST.addAsChildOf(parent,classAbs);
        }
        return classAbs;

    }

    private static MethodAbs parseMethod(String className, ServiceAST parent, String simpleMethodName, ServiceAST serviceAST,int executionTime){

        String methodName = className + "." +simpleMethodName;
        MethodAbs methodAbs = new MethodAbs();
        methodAbs.setName(methodName);
        Integer overallExecutionTime = executionTime;
        methodAbs.setExecutionTime(overallExecutionTime);

        if(!serviceAST.contains(methodAbs)){
            serviceAST.addAsChildOf(parent,methodAbs);
        }
        return methodAbs;

    }

    private static PackageAbs parsePackage(String[] methodSignature, ServiceAST serviceAST){

        PackageAbs currentPackage = new PackageAbs();
        String currentPackageName = methodSignature[0];
        currentPackage.setName(currentPackageName);

        if(!serviceAST.contains(currentPackage)){
            serviceAST.add(currentPackage);
        }

        for(int i = 1; i < methodSignature.length - 3; i++){
            PackageAbs newPackage = new PackageAbs();
            String packageName = currentPackageName + "." + methodSignature[i];
            newPackage.setName(packageName);

            if(!serviceAST.contains(newPackage)){
                serviceAST.addAsChildOf(currentPackage,newPackage);
            }

            currentPackageName = packageName;
            currentPackage = newPackage;

        }
        return currentPackage;

    }




}
