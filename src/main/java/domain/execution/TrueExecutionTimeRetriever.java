package domain.execution;

import domain.MethodBean;
import domain.abstraction.MethodAbs;

import java.util.HashMap;
import java.util.Map;

public class TrueExecutionTimeRetriever {
    private Map<MethodAbs, Long> mapMethodToSpeed;

    public TrueExecutionTimeRetriever(){
        mapMethodToSpeed = new HashMap<>();
    }

    public void addMethod(MethodBean methodBean, Long exTime){
        mapMethodToSpeed.put(methodBean.getMethodAbs(),exTime);
    }

    public Long retrieveExecutionTime(MethodAbs methodAbs){
        return mapMethodToSpeed.get(methodAbs);
    }
}