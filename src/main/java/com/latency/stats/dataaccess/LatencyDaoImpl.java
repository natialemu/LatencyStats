package com.latency.stats.dataaccess;

import com.latency.stats.dataaccess.entity.MethodEntity;
import com.latency.stats.dataaccess.repository.LatencyStatsRepository;
import com.latency.stats.domain.MethodBean;
import com.latency.stats.domain.abstraction.MethodAbs;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LatencyDaoImpl implements LatencyDAO {

    @Autowired
    private LatencyStatsRepository latencyStatsRepository;
    @Override
    public List<MethodBean> getOrderedMethods(String requestID, String appName) {

        List<MethodEntity> methodEntities = latencyStatsRepository.findAllByAppNameAndRequestID();

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
