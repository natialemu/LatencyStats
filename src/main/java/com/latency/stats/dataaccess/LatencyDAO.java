package com.latency.stats.dataaccess;

import com.latency.stats.dataaccess.entity.MethodEntity;
import com.latency.stats.dataaccess.repository.LatencyStatsRepository;
import com.latency.stats.domain.MethodBean;
import com.latency.stats.domain.abstraction.MethodAbs;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LatencyDAO {

    @Autowired
    private LatencyStatsRepository latencyStatsRepository;

    public List<MethodBean> getOrderedMethods(String requestID, String appName) {

        List<MethodEntity> methodEntities = latencyStatsRepository.findAllByAppNameAndRequestID(appName,Long.parseLong(requestID));

        List<MethodBean> methodBeans = new ArrayList<>();
        generateMethodBeans(methodEntities,methodBeans);

        Collections.sort(methodBeans);

        return methodBeans;

    }

    private void generateMethodBeans(List<MethodEntity> methodEntities,List<MethodBean> methodBeans) {

        for(MethodEntity entity: methodEntities){

            MethodBean callMethodBean = new MethodBean(); // openning methodBean
            MethodBean endMethodBean = new MethodBean(); // closing methodBean

            MethodAbs methodAbs = new MethodAbs();
            methodAbs.setName(entity.getMethodName());
            methodAbs.setExecutionTime(entity.getStackPopTime() - entity.getStackPushTime());

            callMethodBean.setMethodAbs(methodAbs);
            endMethodBean.setMethodAbs(methodAbs);

            callMethodBean.setStackRank(entity.getStackPushRank());
            callMethodBean.setPushedOntoStack(true);

            endMethodBean.setStackRank(entity.getStackPopRank());
            endMethodBean.setPushedOntoStack(false);

        }

    }

    public void saveMethod(MethodEntity entity) {
        if(latencyStatsRepository.countDistinctByAppNameAndRequestIDAndMethodName(entity.getAppName(),entity.getRequestID(),entity.getMethodName()) > 0){
            MethodEntity preUpdateEntity = latencyStatsRepository.getMethodEntitiesByMethodNameAndRequestID(entity.getMethodName(),entity.getRequestID());
            long preUpdateAvgRumtime = preUpdateEntity.getAvgMethodRuntime();
            entity.setOverallNumCalls(preUpdateEntity.getOverallNumCalls() + 1);
            long newAvgRuntime = preUpdateAvgRumtime*preUpdateEntity.getOverallNumCalls()/(entity.getOverallNumCalls());
            entity.setNumCallsPerRequest(entity.getNumCallsPerRequest() + 1);
            entity.setAvgMethodRuntime(newAvgRuntime);
        }

        latencyStatsRepository.save(entity);

    }

    public long getAvgExecutionTime(MethodAbs root, long requestID) {

        MethodEntity entity = latencyStatsRepository.getMethodEntitiesByMethodNameAndRequestID(root.getName(),requestID);
        return entity.getAvgMethodRuntime();
    }
}
