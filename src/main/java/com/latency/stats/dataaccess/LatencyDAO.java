package com.latency.stats.dataaccess;

import com.latency.stats.dataaccess.entity.MethodEntity;
import com.latency.stats.dataaccess.repository.LatencyStatsRepository;
import com.latency.stats.domain.MethodBean;
import com.latency.stats.domain.abstraction.MethodAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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

            MethodBean callMethodBean = new MethodBean();
            MethodBean endMethodBean = new MethodBean();

            MethodAbs methodAbs = new MethodAbs();
            methodAbs.setMethodName(entity.getMethodName());
            methodAbs.setExecutionTime(entity.getStackPopTime() - entity.getStackPushTime());

            callMethodBean.setMethodAbs(methodAbs);
            endMethodBean.setMethodAbs(methodAbs);

            callMethodBean.setStackRank(entity.getStackPushRank());
            callMethodBean.setPushedOntoStack(true);

            endMethodBean.setStackRank(entity.getStackPopRank());
            endMethodBean.setPushedOntoStack(false);


        }

    }


    /**
     * Other methods we need:
     *   1. method to save a single method:
     *        check if it exists
     *           if it does then only update
     *           if not then create entry
     *
     *
     */

}
