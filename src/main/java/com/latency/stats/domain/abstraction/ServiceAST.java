package com.latency.stats.domain.abstraction;

import com.latency.stats.domain.builder.AstBuilder;

import java.util.List;

public interface ServiceAST {
    void add(ServiceAST abstraction);
    long getExecusionTime();
    String getName();
    void setName(String name);
    List<ServiceAST> getChildren();
    void setExecutionTime(long totalTime);
    static AstBuilder builder(){
        return new AstBuilder();
    }
}
