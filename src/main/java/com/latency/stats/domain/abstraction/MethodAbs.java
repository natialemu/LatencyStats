package com.latency.stats.domain.abstraction;

import java.util.ArrayList;
import java.util.List;

public class MethodAbs implements ServiceAST, Comparable {

    private String methodName;
    private Long executionTime;

    public MethodAbs(){

    }

    public MethodAbs(String methodName, long executionTime){
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

    @Override
    public int compareTo(Object o) {
        MethodAbs other = (MethodAbs) o;
        return executionTime.compareTo(other.getExecusionTime());
    }
}
