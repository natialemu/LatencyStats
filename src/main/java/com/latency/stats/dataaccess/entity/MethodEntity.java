package com.latency.stats.dataaccess.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(
        name = "method_desc"
)
public class MethodEntity {

    @Id
    @Column(
            name = "request_id"
    )
    private Long requestID;

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
    private Integer stackPopRank;

    @Column(
            name = "stack_push_rank"
    )
    private Integer stackPushRank;

    @Column(
            name = "stack_pop_time"
    )
    private Long stackPopTime;

    @Column(
            name = "stack_push_time"
    )
    private Long stackPushTime;

    @Column(
            name = "avg_method_runtime"
    )
    private Long avgMethodRuntime;

    @Column(
            name = "execution_count_per_request"
    )
    private Integer numCallsPerRequest;

    @Column(
            name = "is_normal_request"
    )
    private Boolean isNormalRequest;

    protected MethodEntity() {}


    public MethodEntity(Long requestID, String appName, String methodName, Integer stackPopRank, Integer stackPushRank, Long stackPopTime, Long stackPushTime, Long avgMethodRuntime, Integer numCallsPerRequest, Boolean isNormalRequest) {
        this.requestID = requestID;
        this.appName = appName;
        this.methodName = methodName;
        this.stackPopRank = stackPopRank;
        this.stackPushRank = stackPushRank;
        this.stackPopTime = stackPopTime;
        this.stackPushTime = stackPushTime;
        this.avgMethodRuntime = avgMethodRuntime;
        this.numCallsPerRequest = numCallsPerRequest;
        this.isNormalRequest = isNormalRequest;
    }

    public MethodEntity(Long requestID, String appName, String methodName, Integer stackPopRank, Integer stackPushRank, Long stackPopTime, Long stackPushTime) {
        this.requestID = requestID;
        this.appName = appName;
        this.methodName = methodName;
        this.stackPopRank = stackPopRank;
        this.stackPushRank = stackPushRank;
        this.stackPopTime = stackPopTime;
        this.stackPushTime = stackPushTime;
    }

    public Long getRequestID() {
        return requestID;
    }

    public void setRequestID(Long requestID) {
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

    public Integer getStackPopRank() {
        return stackPopRank;
    }

    public void setStackPopRank(Integer stackPopRank) {
        this.stackPopRank = stackPopRank;
    }

    public Integer getStackPushRank() {
        return stackPushRank;
    }

    public void setStackPushRank(Integer stackPushRank) {
        this.stackPushRank = stackPushRank;
    }

    public Long getStackPopTime() {
        return stackPopTime;
    }

    public void setStackPopTime(Long stackPopTime) {
        this.stackPopTime = stackPopTime;
    }

    public Long getStackPushTime() {
        return stackPushTime;
    }

    public void setStackPushTime(Long stackPushTime) {
        this.stackPushTime = stackPushTime;
    }

    public Long getAvgMethodRuntime() {
        return avgMethodRuntime;
    }

    public void setAvgMethodRuntime(Long avgMethodRuntime) {
        this.avgMethodRuntime = avgMethodRuntime;
    }

    public Integer getNumCallsPerRequest() {
        return numCallsPerRequest;
    }

    public void setNumCallsPerRequest(Integer numCallsPerRequest) {
        this.numCallsPerRequest = numCallsPerRequest;
    }

    public Boolean isNormalRequest() {
        return isNormalRequest;
    }

    public void setNormalRequest(Boolean normalRequest) {
        isNormalRequest = normalRequest;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MethodEntity that = (MethodEntity) o;
//        return requestID == that.requestID &&
//                stackPopRank == that.stackPopRank &&
//                stackPushRank == that.stackPushRank &&
//                stackPopTime == that.stackPopTime &&
//                stackPushTime == that.stackPushTime &&
//                avgMethodRuntime == that.avgMethodRuntime &&
//                numCallsPerRequest == that.numCallsPerRequest &&
//                isNormalRequest == that.isNormalRequest &&
//                Objects.equals(appName, that.appName) &&
//                Objects.equals(methodName, that.methodName);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(requestID, appName, methodName, stackPopRank, stackPushRank, stackPopTime, stackPushTime, avgMethodRuntime, numCallsPerRequest, isNormalRequest);
//    }
}
