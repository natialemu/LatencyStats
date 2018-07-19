package com.latency.stats.domain.execution;

import com.latency.stats.domain.MethodBean;
import com.latency.stats.domain.builder.ExecutionTreeBuilder;

import java.util.List;

public interface ExTree {

    List<ExTree> getChildren();

    MethodBean getMethod();

    void addChild(ExTree tree);

    static ExecutionTreeBuilder builder(){
        return new ExecutionTreeBuilder();
    }

    boolean hasChildren();



}
