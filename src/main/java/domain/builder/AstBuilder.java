package domain.builder;

import dal.LatencyDAO;
import dal.LatencyDaoImpl;
import domain.MethodBean;
import domain.abstraction.*;
import java.util.List;

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

        return root;

    }

    private void insertIntoApplication(List<ServiceAST> components) {

        ServiceAST currentRoot = root;
        for(ServiceAST component: components){

            if(!currentRoot.getChildren().contains(component)){
                currentRoot.add(component);
            }
            currentRoot = component;
        }

    }




}
