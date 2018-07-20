package com.latency.stats.service.representation.response;


import com.latency.stats.service.representation.response.stats.mini.ApplicationStatsMini;
import com.latency.stats.service.representation.response.stats.mini.ClassStatsMini;
import com.latency.stats.service.representation.response.stats.mini.MethodStatsMini;
import com.latency.stats.service.representation.response.stats.mini.PackageStatsMini;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GeneralReport {

    private String requestID;
    private ApplicationStatsMini overallAppStats;
    private MethodStatsMini methodStats;
    private ClassStatsMini classStats;
    private PackageStatsMini packageStats;




}
