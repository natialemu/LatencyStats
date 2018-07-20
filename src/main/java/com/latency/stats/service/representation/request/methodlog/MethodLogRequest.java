package com.latency.stats.service.representation.request.methodlog;

public class MethodLogRequest {
    private MethodLogRequestBody methodLogRequestBody;
    private MethodLogFilter filter;
    private MethodLogHeaders headers;

    public MethodLogRequest(MethodLogRequestBody body, MethodLogFilter filters, MethodLogHeaders headers){
        this.methodLogRequestBody = body;
        this.filter = filters;
        this.headers = headers;
    }


    public MethodLogRequestBody getMethodLogRequestBody() {
        return methodLogRequestBody;
    }

    public void setMethodLogRequestBody(MethodLogRequestBody methodLogRequestBody) {
        this.methodLogRequestBody = methodLogRequestBody;
    }

    public MethodLogFilter getFilter() {
        return filter;
    }

    public void setFilter(MethodLogFilter filter) {
        this.filter = filter;
    }

    public MethodLogHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(MethodLogHeaders headers) {
        this.headers = headers;
    }

    public static MethodLogRequestBuilder builder() {
        return new MethodLogRequestBuilder();
    }


    public static class MethodLogRequestBuilder{
        private MethodLogRequestBody thisBody;
        private MethodLogFilter thisFilters;
        private MethodLogHeaders thisHeaders;


        public MethodLogRequest.MethodLogRequestBuilder filters(MethodLogFilter filters) {
            thisFilters = filters;
            return this;
        }

        public MethodLogRequest.MethodLogRequestBuilder headers(MethodLogHeaders headers) {
            thisHeaders = headers;
            return this;
        }
        public MethodLogRequest.MethodLogRequestBuilder body(MethodLogRequestBody body) {
            thisBody = body;
            return this;
        }

        public MethodLogRequest buildRequest(){

            return new MethodLogRequest(thisBody,thisFilters,thisHeaders);
        }


    }
}
