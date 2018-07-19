package com.latency.stats.representation.stats.mini;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class MethodStatsMini {

    private Map<String,Long> tenPercSlowestMethods;
    private Map<String,Long> criticalMethods;
}
