package com.latency.stats.service.representation.request.methodlog;

public class MethodLogFilter {
    private MethodLogFilter(){

    }

    public static MethodLogFilter.FiltersBuilder builder(){return new FiltersBuilder();}

    public static class FiltersBuilder{

        public MethodLogFilter build(){
            return new MethodLogFilter();
        }

    }
}
