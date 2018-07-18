package domain.builder;

import domain.abstraction.MethodAbs;
import domain.abstraction.ServiceAST;
import domain.execution.Empty;
import domain.execution.ExTree;
import domain.execution.TrueExecutionTimeRetriever;

import java.util.List;

public class ExecutionTimeHelper {
    private static ExTree exTree;
    private static ServiceAST service;
    private static TrueExecutionTimeRetriever executionTimeRetriever;

    public ExecutionTimeHelper(String appName, String requestID){
        ExecutionTreeBuilder builder = ExTree.builder();
        exTree = builder
                .withAppName(appName)
                .withRequestId(requestID)
                .withExecutionTimeGenerated(true)
                .buildAndRetrieveExecutionTree();
        executionTimeRetriever = builder.getExecutionTimeRetriever();

        service = ServiceAST.builder()
                .withApplicationName(appName)
                .getService();

        correctExecutiontime(service);
    }


    public ServiceAST getService(){
        return service;
    }

    private static void correctExecutiontime(ServiceAST service) {

        if(service instanceof MethodAbs){
            long exTime = executionTimeRetriever.retrieveExecutionTime((MethodAbs) service);
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
