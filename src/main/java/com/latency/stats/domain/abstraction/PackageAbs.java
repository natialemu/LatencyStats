package com.latency.stats.domain.abstraction;

import java.util.ArrayList;
import java.util.List;

public class PackageAbs implements ServiceAST {

    private List<ServiceAST> abstractions;
    private String packageName;
    private long executionTime;

    public PackageAbs(){
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
        return packageName;
    }

    @Override
    public void setName(String name) {
        this.packageName = name;
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
