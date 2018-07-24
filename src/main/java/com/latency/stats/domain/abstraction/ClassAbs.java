package com.latency.stats.domain.abstraction;

import java.util.ArrayList;
import java.util.List;

public class ClassAbs implements ServiceAST {

    private List<ServiceAST> abstractions;
    private String applicationName;
    private long executionTime;

    public ClassAbs(){
        abstractions = new ArrayList<>();
    }

    @Override
    public void add(ServiceAST abstraction) {
        abstractions.add(abstraction);
    }

    @Override
    public long getExecusionTime() {
        return executionTime;
    }

    @Override
    public String getName() {
        return applicationName;
    }

    @Override
    public void setName(String name) {
        applicationName = name;
    }
    @Override
    public List<ServiceAST> getChildren() {
        return abstractions;
    }

    @Override
    public void setExecutionTime(long totalTime) {

        executionTime = totalTime;
    }
}
