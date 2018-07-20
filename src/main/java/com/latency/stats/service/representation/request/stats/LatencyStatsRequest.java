package com.latency.stats.service.representation.request.stats;

public class LatencyStatsRequest {
    private LatencyStatsRequestBody body;
    private Filters filters;
    private Headers headers;

    public LatencyStatsRequestBody getBody() {
        return body;
    }

    public Filters getFilters() {
        return filters;
    }

    public Headers getHeaders() {
        return headers;
    }

    public LatencyStatsRequest(LatencyStatsRequestBody body, Filters filters, Headers headers){
        this.body = body;
        this.filters = filters;
        this.headers = headers;
    }
    public static LatencyStatsRequestBuilder builder() {
        return new LatencyStatsRequestBuilder();
    }


    public static class LatencyStatsRequestBuilder{
        private LatencyStatsRequestBody thisBody;
        private Filters thisFilters;
        private Headers thisHeaders;


        public LatencyStatsRequest.LatencyStatsRequestBuilder filters(Filters filters) {
            thisFilters = filters;
            return this;
        }

        public LatencyStatsRequest.LatencyStatsRequestBuilder headers(Headers headers) {
            thisHeaders = headers;
            return this;
        }
        public LatencyStatsRequest.LatencyStatsRequestBuilder body(LatencyStatsRequestBody body) {
            thisBody = body;
            return this;
        }

        public LatencyStatsRequest buildRequest(){

            return new LatencyStatsRequest(thisBody,thisFilters,thisHeaders);
        }


    }
}
