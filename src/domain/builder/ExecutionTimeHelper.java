package domain.builder;

import domain.abstraction.MethodAbs;
import domain.abstraction.ServiceAST;
import domain.execution.ExecutionGraph;

import java.util.List;

public class ExecutionTimeHelper {
    private static ExecutionGraph graph = ExecutionGraphBuilder.getExecutionGraph();
    private static ServiceAST service = AstBuilder.getService();


    public static ServiceAST getTimeAdjustedService(){
        correctExecutiontime(service);
        return service;
    }

    private static void correctExecutiontime(ServiceAST service) {
        List<ServiceAST> children =  service.getChildren();
        if(children.size() == 0){
            MethodAbs method = (MethodAbs) service;
            List<String> calledMethods = graph.getMethodsCalledBy(method);
            Integer childrenExecutionTime = 0;
            for(String calledMethodName: calledMethods){
                MethodAbs calledMethod = service.getMethod(calledMethodName);
                childrenExecutionTime += calledMethod.getExecusionTime();

            }
            method.setExecutionTime(method.getExecusionTime() - childrenExecutionTime);
        }else{
            for(ServiceAST child : children){
                correctExecutiontime(child);
                updateExecutionTime(service);
            }
        }

    }

    private static void updateExecutionTime(ServiceAST service) {
        int totalTime = 0;
        for(ServiceAST serviceAST: service.getChildren()){
            totalTime += serviceAST.getExecusionTime();
        }
        service.setExecutionTime(totalTime);
    }
}
