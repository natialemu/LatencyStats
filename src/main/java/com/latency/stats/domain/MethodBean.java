package com.latency.stats.domain;

import com.latency.stats.domain.abstraction.MethodAbs;

import java.util.Objects;

public class MethodBean implements Comparable{


    private MethodAbs methodAbs;
    private long overallExecutionTime;
    private boolean pushedOntoStack;
    private boolean stackRank;

    public MethodAbs getMethodAbs() {
        return methodAbs;
    }

    public void setMethodAbs(MethodAbs methodAbs) {
        this.methodAbs = methodAbs;
    }

    public long getOverallExecutionTime() {
        return overallExecutionTime;
    }

    public void setOverallExecutionTime(long overallExecutionTime) {
        this.overallExecutionTime = overallExecutionTime;
    }

    public boolean isPushedOntoStack() {
        return pushedOntoStack;
    }

    public void setPushedOntoStack(boolean pushedOntoStack) {
        this.pushedOntoStack = pushedOntoStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodBean that = (MethodBean) o;
        return Objects.equals(methodAbs, that.methodAbs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(methodAbs);
    }

    public boolean isStackRank() {
        return stackRank;

    }

    public void setStackRank(boolean stackRank) {
        this.stackRank = stackRank;
    }

    @Override
    public int compareTo(Object o) {
        return 0;//TODO
    }
}
