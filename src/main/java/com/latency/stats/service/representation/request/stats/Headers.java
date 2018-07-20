package com.latency.stats.service.representation.request.stats;

public class Headers {
    private boolean returnSuccessForMissingReport;

    private Headers(boolean returnSuccessForMissingReport){
        this.returnSuccessForMissingReport = returnSuccessForMissingReport;
    }

    public static HeadersBuilder builder(){return new HeadersBuilder();}

    public static class HeadersBuilder{
        private boolean thisReturnSuccessForMissingReport;

        public Headers.HeadersBuilder returnSuccessForMissingProperties(boolean returnSuccessForMissingReport) {
            thisReturnSuccessForMissingReport = returnSuccessForMissingReport;
            return this;
        }

        public Headers build(){
            return new Headers(thisReturnSuccessForMissingReport);
        }

        @Override
        public String toString() {
            return "HeadersBuilder{" +
                    "thisReturnSuccessForMissingReport=" + thisReturnSuccessForMissingReport +
                    '}';
        }
    }
}
