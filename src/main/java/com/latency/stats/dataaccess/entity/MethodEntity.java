package com.latency.stats.dataaccess.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class MethodEntity implements Serializable {

    @Id
    @Column(
            name = "request_id"
    )
    private long requestID;

    @Column(
            name = "app_name"
    )
    private String appName;

    @Column(
            name = "method_name"
    )
    private String methodName;

    @Column(
            name = "stack_pop_rank"
    )
    private int stackPopRank;

    @Column(
            name = "stack_push_rank"
    )
    private int stackPushRank;

    @Column(
            name = "stack_pop_time"
    )
    private int stackPopTime;

    @Column(
            name = "stack_push_time"
    )
    private int stackPushTime;

    @Column(
            name = "avg_method_runtime"
    )
    private long avgMethodRuntime;

    @Column(
            name = "execution_count_per_request"
    )
    private int numCallsPerRequest;

    @Column(
            name = "is_normal_request"
    )
    private boolean isNormalRequest;

    public MethodEntity(long requestID) {
        this.requestID = requestID;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getStackPopRank() {
        return stackPopRank;
    }

    public void setStackPopRank(int stackPopRank) {
        this.stackPopRank = stackPopRank;
    }

    public int getStackPushRank() {
        return stackPushRank;
    }

    public void setStackPushRank(int stackPushRank) {
        this.stackPushRank = stackPushRank;
    }

    public int getStackPopTime() {
        return stackPopTime;
    }

    public void setStackPopTime(int stackPopTime) {
        this.stackPopTime = stackPopTime;
    }

    public int getStackPushTime() {
        return stackPushTime;
    }

    public void setStackPushTime(int stackPushTime) {
        this.stackPushTime = stackPushTime;
    }

    public long getAvgMethodRuntime() {
        return avgMethodRuntime;
    }

    public void setAvgMethodRuntime(long avgMethodRuntime) {
        this.avgMethodRuntime = avgMethodRuntime;
    }

    public int getNumCallsPerRequest() {
        return numCallsPerRequest;
    }

    public void setNumCallsPerRequest(int numCallsPerRequest) {
        this.numCallsPerRequest = numCallsPerRequest;
    }

    public boolean isNormalRequest() {
        return isNormalRequest;
    }

    public void setNormalRequest(boolean normalRequest) {
        isNormalRequest = normalRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodEntity that = (MethodEntity) o;
        return requestID == that.requestID &&
                stackPopRank == that.stackPopRank &&
                stackPushRank == that.stackPushRank &&
                stackPopTime == that.stackPopTime &&
                stackPushTime == that.stackPushTime &&
                avgMethodRuntime == that.avgMethodRuntime &&
                numCallsPerRequest == that.numCallsPerRequest &&
                isNormalRequest == that.isNormalRequest &&
                Objects.equals(appName, that.appName) &&
                Objects.equals(methodName, that.methodName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(requestID, appName, methodName, stackPopRank, stackPushRank, stackPopTime, stackPushTime, avgMethodRuntime, numCallsPerRequest, isNormalRequest);
    }
}
