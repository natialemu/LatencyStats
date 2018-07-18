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
    }


    public ServiceAST getTimeAdjustedService(){
        correctExecutiontime(service);
        return service;
    }

    private static void correctExecutiontime(ServiceAST service) {
        //TODO
        List<ServiceAST> children =  service.getChildren();
        if(children.size() == 0){
            MethodAbs method = (MethodAbs) service;
            ExTree targetSubTree = getTree(method, exTree);
            List<ExTree> calledMethods = targetSubTree.getChildren();
            //replacement is traverse the root until you get node containing method
            //then get its children
            Integer childrenExecutionTime = 0;
            for(ExTree tree: calledMethods){
                String calledMethodName = tree.getMethod().getMethodAbs().getName();
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

    /*
       Get the sub tree rooted at a node which contains method
     */
    private static ExTree getTree(MethodAbs method, ExTree graph) {
        if(graph.getMethod().getMethodAbs().equals(method)){
            return graph;
        }

        for(ExTree children: graph.getChildren()){
            return getTree(method,children);
        }
        return new Empty();

    }

    private static void updateExecutionTime(ServiceAST service) {
        int totalTime = 0;
        for(ServiceAST serviceAST: service.getChildren()){
            totalTime += serviceAST.getExecusionTime();
        }
        service.setExecutionTime(totalTime);
    }
}
