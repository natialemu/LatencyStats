package com.latency.stats.service.representation.request.methodlog;

public class MethodLogRequestBody {

    private String appName;
    private String requestId;
    private String fullBeforeMethodName;
    private long calledMethodStartTime;
    private long calledMethodEndtime;
    private int pushStackRank;
    private int popStackRank;

    public MethodLogRequestBody(String appName,String requestId,String fullBeforeMethodName, long calledMethodStartTime, long calledMethodEndtime,
                                int pushStackRank, int popStackRank){
        this.appName = appName;
        this.requestId = requestId;
        this.fullBeforeMethodName = fullBeforeMethodName;
        this.calledMethodEndtime = calledMethodEndtime;
        this.calledMethodStartTime = calledMethodStartTime;
        this.pushStackRank = pushStackRank;
        this.popStackRank = popStackRank;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getFullBeforeMethodName() {
        return fullBeforeMethodName;
    }

    public void setFullBeforeMethodName(String fullBeforeMethodName) {
        this.fullBeforeMethodName = fullBeforeMethodName;
    }

    public long getCalledMethodStartTime() {
        return calledMethodStartTime;
    }

    public void setCalledMethodStartTime(long calledMethodStartTime) {
        this.calledMethodStartTime = calledMethodStartTime;
    }

    public long getCalledMethodEndtime() {
        return calledMethodEndtime;
    }

    public void setCalledMethodEndtime(long calledMethodEndtime) {
        this.calledMethodEndtime = calledMethodEndtime;
    }

    public int getPushStackRank() {
        return pushStackRank;
    }

    public void setPushStackRank(int pushStackRank) {
        this.pushStackRank = pushStackRank;
    }

    public int getPopStackRank() {
        return popStackRank;
    }

    public void setPopStackRank(int popStackRank) {
        this.popStackRank = popStackRank;
    }


//    public static MethodLogRequest buildRequest(String appName, String requestId, String fullBeforeMethodName, long calledMethodStartTime, long calledMethodEndTime, int stackPushRank, int stackPopRank){
}
