package com.latency.stats.dataaccess;

import com.latency.stats.domain.MethodBean;

import java.util.List;

public interface LatencyDAO {

    List<MethodBean> getOrderedMethods(String requestID, String appName );

}
