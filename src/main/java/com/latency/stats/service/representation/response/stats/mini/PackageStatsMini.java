package com.latency.stats.service.representation.response.stats.mini;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Setter
@Getter
public class PackageStatsMini {

    private Map<String,Long> criticalPackages;


    public void addCriticalPackages(String name, long execusionTime) {
        criticalPackages.put(name,execusionTime);
    }
}
