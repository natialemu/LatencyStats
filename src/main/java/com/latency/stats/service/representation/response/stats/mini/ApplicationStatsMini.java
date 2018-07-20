package com.latency.stats.service.representation.response.stats.mini;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationStatsMini {

    private String applicationName;
    private long overallRuntime;

}
