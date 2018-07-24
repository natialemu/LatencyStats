package com.latency.stats.dataaccess.repository;


import com.latency.stats.dataaccess.entity.MethodEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LatencyStatsRepository  extends CrudRepository<MethodEntity, Long> {

    List<MethodEntity> findAllByAppNameAndRequestID(String appName, long requestID);

    Integer countDistinctByAppNameAndRequestIDAndMethodName(String appName,long requestID,String methodName);

    MethodEntity save(MethodEntity methodEntity);

    MethodEntity getMethodEntitiesByMethodNameAndRequestID(String methodName, long requestID);

}
