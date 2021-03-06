package com.latency.stats.service.representation.response.stats.mini;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class MethodStatsMini {

    private Map<String,Long> tenPercSlowestMethods;
    private Map<String,Long> criticalMethods;

    public void addCriticalMethods(String methodName, long executionTime) {
        criticalMethods.put(methodName, executionTime);
    }

    public void addSlowMethod(String methodName, long executionTime){
        tenPercSlowestMethods.put(methodName, executionTime);
    }
}
