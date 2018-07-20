package com.latency.stats.service.representation.request.methodlog;

public class MethodLogHeaders {



    private MethodLogHeaders(){

    }

    public static HeadersBuilder builder(){return new HeadersBuilder();}

    public static class HeadersBuilder{

        public MethodLogHeaders.HeadersBuilder returnSuccessForMissingProperties() {
            return this;
        }

        public MethodLogHeaders build(){
            return new MethodLogHeaders();
        }

        @Override
        public String toString() {
            return "HeadersBuilder{" +
                    '}';
        }
    }
}
