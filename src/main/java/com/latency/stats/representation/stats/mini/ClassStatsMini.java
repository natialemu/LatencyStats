package com.latency.stats.representation.stats.mini;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class ClassStatsMini {

    private Map<String,Long> tenPercSlowestClasses;
    private Map<String,Long> criticalClas; //if avg performace of all methods affected in class is above threshold


}
