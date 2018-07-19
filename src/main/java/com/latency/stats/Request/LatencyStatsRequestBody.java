package com.latency.stats.Request;

public class LatencyStatsRequestBody {
    private String requestID;

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    private String appName;

    public LatencyStatsRequestBody(String requestID,  String appName){
        this.requestID = requestID;
        this.appName = appName;

    }

}
