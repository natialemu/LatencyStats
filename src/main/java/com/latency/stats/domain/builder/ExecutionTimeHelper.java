package com.latency.stats.domain.builder;


import com.latency.stats.domain.abstraction.MethodAbs;
import com.latency.stats.domain.abstraction.ServiceAST;
import com.latency.stats.domain.execution.ExTree;
import com.latency.stats.domain.execution.TrueExecutionTimeRetriever;
import com.latency.stats.service.representation.request.stats.LatencyStatsRequest;


public class ExecutionTimeHelper {

    public ServiceAST getService(LatencyStatsRequest request){

        ExecutionTreeBuilder builder = ExTree.builder();
        builder
                .withAppName(request.getBody().getAppName())
                .withRequestId(request.getBody().getRequestID())
                .withExecutionTimeGenerated(true)
                .generateExecutionTimeRetriver();

        ServiceAST service = ServiceAST.builder()
                .withApplicationName(request.getBody().getAppName())
                .withRequestID(request.getBody().getRequestID())
                .getService();
        correctExecutiontime(service);

        return service;
    }

    private static void correctExecutiontime(ServiceAST service) {

        if(service instanceof MethodAbs){
            long exTime = TrueExecutionTimeRetriever.retrieveExecutionTime((MethodAbs) service);
            service.setExecutionTime(exTime);
            return;
        }
        long overallComponentTime = 0;

        for(ServiceAST component: service.getChildren()){

            correctExecutiontime(component);
            overallComponentTime += component.getExecusionTime();

        }

        service.setExecutionTime(overallComponentTime);

    }

}
