package com.latency.stats.dataaccess.entity;

import java.io.Serializable;
import java.util.Objects;

public class MethodID implements Serializable {

    private Long requestID;
    private String appName;
    private String methodName;

    public MethodID() { }

    public MethodID(Long requestID, String appName, String methodName) {
        this.requestID = requestID;
        this.appName = appName;
        this.methodName = methodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodID methodID = (MethodID) o;
        return Objects.equals(requestID, methodID.requestID) &&
                Objects.equals(appName, methodID.appName) &&
                Objects.equals(methodName, methodID.methodName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(requestID, appName, methodName);
    }
}
