package com.latency.stats.dataaccess.repository;


import com.latency.stats.dataaccess.entity.MethodEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LatencyStatsRepository  extends CrudRepository<MethodEntity, Long> {

    List<MethodEntity> findAllByAppNameAndRequestID();
}
