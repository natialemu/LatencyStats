package domain.builder;

import domain.abstraction.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AstBuilder {

    private static final String LATENCY_LOG_FILE = "/Users/nalemu/Documents/EWE/dayaway/Logs/latency_log.csv";

    public static ServiceAST getService(){
        ServiceAST serviceAST=null;
        try {
            File file =
                    new File(LATENCY_LOG_FILE);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){

                String[] currentLine = sc.nextLine().split(",");
                assert(currentLine.length == 3);

                String[] methodSignature = currentLine[0].split(".");
                String applicationName = currentLine[1];

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
