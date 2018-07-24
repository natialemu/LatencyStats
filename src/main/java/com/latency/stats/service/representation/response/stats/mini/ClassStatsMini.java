package com.latency.stats.service.representation.response.stats.mini;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class ClassStatsMini {

    private Map<String,Long> criticalClas; //if avg performace of all methods affected in class is above threshold


    public void addCriticalClasses(String name, long execusionTime) {
        criticalClas.put(name,execusionTime);
    }
}
