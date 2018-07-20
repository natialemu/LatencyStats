package com.latency.stats.service.representation.request.methodlog;

public class MethodLogRequestBuilder {

    private MethodLogRequestBuilder() {

    }

    public static MethodLogRequest buildRequest(String appName, String requestId, String fullBeforeMethodName, long calledMethodStartTime, long calledMethodEndTime, int stackPushRank, int stackPopRank){

        return MethodLogRequest.builder()
                .body(new MethodLogRequestBody(appName,requestId,fullBeforeMethodName,calledMethodStartTime,calledMethodEndTime,stackPushRank,stackPopRank))
                .filters(buildFilter())
                .headers(buildHeader())
                .buildRequest();
    }



    public static MethodLogFilter buildFilter() {
        return MethodLogFilter.builder()
                .build();
    }

    private static MethodLogHeaders buildHeader() {
        return MethodLogHeaders.builder()
                .returnSuccessForMissingProperties()
                .build();
    }
}

