package com.latency.stats.service.representation.request.stats;

public class Filters {

    private Filters(){

    }

    public static Filters.FiltersBuilder builder(){return new FiltersBuilder();}

    public static class FiltersBuilder{

        public Filters build(){
            return new Filters();
        }

    }
}
