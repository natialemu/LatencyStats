package com.latency.stats.domain;

import com.latency.stats.domain.abstraction.ApplicationAbs;
import com.latency.stats.domain.abstraction.ClassAbs;
import com.latency.stats.domain.abstraction.MethodAbs;
import com.latency.stats.domain.abstraction.ServiceAST;

import java.util.List;

public class LatencyStatsFacade {

    private ServiceAST service;

    public LatencyStatsFacade(ServiceAST service){
        this.service = service;

    }


    public MethodAbs getSlowestMethod() { return null; }

    public List<MethodAbs> getNSlowestMethods(int n) {
        return null;
    }

    public List<ClassAbs> getNSlowestClass(int n) {
        return null;
    }

    public ClassAbs getSlowestClass() {
        return null;
    }

    public ApplicationAbs getSlowestApplication() {
        return null;
    }
}
