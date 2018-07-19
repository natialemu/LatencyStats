package com.latency.stats.domain.builder;

import com.latency.stats.domain.abstraction.ClassAbs;
import com.latency.stats.domain.abstraction.MethodAbs;
import com.latency.stats.domain.abstraction.PackageAbs;
import com.latency.stats.domain.abstraction.ServiceAST;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ServiceComponentBuilder {
    private String completeMethodDefinition;

    public ServiceComponentBuilder(String completeMethodDefinition){
        this.completeMethodDefinition = completeMethodDefinition;

    }

    public List<ServiceAST> getComponents() {

        String[] splitUpDefinition = completeMethodDefinition.split(".");
        Stack<ServiceAST> componentStack = new Stack<>();

        int i = splitUpDefinition.length-1;
        i  = parseMethodAndClass(i,splitUpDefinition,componentStack);
        i = parsePackages(i,splitUpDefinition,componentStack);

        List<ServiceAST> components = new ArrayList<>();
        while(!componentStack.empty()){
            ServiceAST component = componentStack.pop();
            components.add(component);
        }
        return components;
    }

    private int parsePackages(int index, String[] splitUpDefinition, Stack<ServiceAST> componentStack) {
        for(int i = index; i >= 0; i--){
            String packageName = splitUpDefinition[i];
            PackageAbs packageAbs = new PackageAbs();
            packageAbs.setName(packageName);
            componentStack.push(packageAbs);
        }
        return -1;
    }

    private int parseMethodAndClass(int index, String[] splitUpDefinition, Stack<ServiceAST> componentStack) {
        if(index < 0){
            return index;
        }
        String[] methodAndClassName = splitUpDefinition[index].split("#");
        MethodAbs methodAbs = new MethodAbs();
        ClassAbs classAbs = new ClassAbs();
        methodAbs.setName(methodAndClassName[2]);
        classAbs.setName(methodAndClassName[1]);
        componentStack.push(methodAbs);
        componentStack.push(classAbs);
        return index-1;
    }
}
