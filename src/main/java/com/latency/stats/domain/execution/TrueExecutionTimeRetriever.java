package com.latency.stats.domain.execution;

import com.latency.stats.domain.MethodBean;
import com.latency.stats.domain.abstraction.MethodAbs;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class TrueExecutionTimeRetriever {
    public static Map<MethodAbs, Long> mapMethodToSpeed = new HashMap<>();

    TrueExecutionTimeRetriever(){
    }

    public static void addMethod(MethodBean methodBean, Long exTime){
        mapMethodToSpeed.put(methodBean.getMethodAbs(),exTime);
    }

    public static Long retrieveExecutionTime(MethodAbs methodAbs){
        return mapMethodToSpeed.get(methodAbs);
    }
}
