package domain.builder;

import dal.LatencyDAO;
import dal.LatencyDaoImpl;
import domain.MethodBean;
import domain.abstraction.*;
import java.util.List;

public class AstBuilder {
    private LatencyDAO latencyDAO;
    private ServiceAST root;
    private String requestID;

    public AstBuilder(){
        latencyDAO = new LatencyDaoImpl();


    }

    public AstBuilder withApplicationName(String applicationName){

        root = new ApplicationAbs(applicationName);
        return this;

    }

    public AstBuilder withRequestID(String requestID){

        this.requestID =requestID;
        return this;

    }


    public  ServiceAST getService(){

        List<MethodBean> methodBeanList=latencyDAO.getOrderedMethods(root.getName(),requestID);

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
