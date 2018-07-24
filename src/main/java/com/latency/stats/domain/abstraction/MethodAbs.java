package com.latency.stats.domain.abstraction;

import com.latency.stats.domain.execution.ExecutionGraph;

import java.util.ArrayList;
import java.util.List;

public class MethodAbs implements ServiceAST {

    private String methodName;
    private long executionTime;

    public MethodAbs(){

    }

    public MethodAbs(String methodName, int executionTime){
        this.methodName = methodName;
        this.executionTime = executionTime;
    }

    @Override
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }


    @Override
    public void add(ServiceAST abstraction) { }

    @Override
    public long getExecusionTime() {
        return executionTime;
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
    public List<ServiceAST> getChildren() {
        return new ArrayList<>();
    }

}
