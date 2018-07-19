package com.latency.stats.domain.execution;

import com.latency.stats.domain.MethodBean;
import com.latency.stats.domain.builder.ExecutionTreeBuilder;

import java.util.ArrayList;
import java.util.List;

public class ConsTree implements ExTree {
    private MethodBean methodBean;
    private List<ExTree> children;

    ConsTree(MethodBean methodBean, List<ExTree> children){
        this.methodBean = methodBean;
        this.children = children;
    }

    public ConsTree(MethodBean methodBean){
        this.methodBean = methodBean;
        children = new ArrayList<>();
    }


    @Override
    public List<ExTree> getChildren() {
        return children;
    }

    @Override
    public MethodBean getMethod() {
        return methodBean;
    }

    @Override
    public void addChild(ExTree child) {
        children.add(child);

    }

    @Override
    public boolean hasChildren() {
        return children.size() != 0;
    }

}
