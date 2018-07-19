package com.latency.stats.representation;


import com.latency.stats.representation.stats.mini.ApplicationStatsMini;
import com.latency.stats.representation.stats.mini.ClassStatsMini;
import com.latency.stats.representation.stats.mini.MethodStatsMini;
import com.latency.stats.representation.stats.mini.PackageStatsMini;

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
