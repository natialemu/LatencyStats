package com.latency.stats.domain.abstraction;

import com.latency.stats.domain.execution.ExecutionGraph;

import java.util.ArrayList;
import java.util.List;

public class MethodAbs implements ServiceAST {



    private String methodName;
    private long executionTime;
    private ExecutionGraph executionGraph;

    public MethodAbs(){

    }

    public MethodAbs(String methodName, int executionTime){
        this.methodName = methodName;
        this.executionTime = executionTime;
        //use ExecusionGraphBuilder to build the graph
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public void setExecutionGraph(ExecutionGraph executionGraph) {
        this.executionGraph = executionGraph;
    }

    @Override
    public void add(ServiceAST abstraction) {
        //TODO

    }

    @Override
    public void getAbstractionInformation() {
        //TODO

    }

    @Override
    public long getExecusionTime() {

        return executionTime;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public MethodAbs getSlowestMethod() {
        return null;
    }

    @Override
    public List<MethodAbs> getNSlowestMethods(int n) {
        return null;
    }

    @Override
    public List<ClassAbs> getNSlowestClass(int n) {
        return null;
    }

    @Override
    public ClassAbs getSlowestClass() {
        return null;
    }

    @Override
    public ApplicationAbs getSlowestApplication() {
        return null;
    }

    @Override
    public String getName() {
        return methodName;
    }

    @Override
    public void setName(String name) {
        methodName = name;
    }

    @Override
    public boolean contains(ServiceAST serviceAST) {
        return false;
    }

    @Override
    public void addAsChildOf(ServiceAST parent, ServiceAST child) {

    }

    @Override
    public List<ServiceAST> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public MethodAbs getMethod(String calledMethodName) {
        return null;
    }
}
