package com.latency.stats.domain.builder;

import com.latency.stats.dataaccess.LatencyDAO;
import com.latency.stats.domain.MethodBean;
import com.latency.stats.domain.abstraction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AstBuilder {

    @Autowired
    private LatencyDAO latencyDAO;
    private ServiceAST root;
    private String requestID;

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
