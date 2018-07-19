package com.latency.stats.domain.execution;

import com.latency.stats.domain.MethodBean;
import com.latency.stats.domain.builder.ExecutionTreeBuilder;

import java.util.List;

public class Empty implements ExTree {

    public Empty(){
        //TODO
    }
    @Override
    public List<ExTree> getChildren() {
        return null;
    }

    @Override
    public MethodBean getMethod() {
        return null;
    }

    @Override
    public void addChild(ExTree tree) {

    }

    @Override
    public boolean hasChildren() {
        return false;
    }


}
