package com.latency.stats.domain.builder;

import com.latency.stats.dataaccess.LatencyDAO;
import com.latency.stats.dataaccess.LatencyDaoImpl;
import com.latency.stats.domain.MethodBean;
import com.latency.stats.domain.execution.ConsTree;
import com.latency.stats.domain.execution.Empty;
import com.latency.stats.domain.execution.ExTree;
import com.latency.stats.domain.execution.TrueExecutionTimeRetriever;

import java.util.List;
import java.util.Stack;

public class ExecutionTreeBuilder {

    private static ExTree root;
    private LatencyDAO latencyDAO;
    private String requestID;
    private String appName;
    private boolean performPostOrder;
    private static TrueExecutionTimeRetriever executionTimeRetriever;

    public ExecutionTreeBuilder(){
        root = new Empty();
        latencyDAO = new LatencyDaoImpl();
        executionTimeRetriever = new TrueExecutionTimeRetriever();
    }

    public ExecutionTreeBuilder withRequestId(String requestID){
        this.requestID = requestID;
        return this;
    }

    public ExecutionTreeBuilder withAppName(String appName){
        this.appName = appName;
        return this;
    }


    public ExTree buildAndRetrieveExecutionTree(){

        List<MethodBean> methods = latencyDAO.getOrderedMethods(requestID,appName);
        assert(methods.size() > 1);
        MethodBean rootMethod = methods.get(0);
        root = new ConsTree(rootMethod);
        buildExecutionTree(methods,1,methods.size()-2,root);
        if(performPostOrder){
            postOrderExecutionTimeGenerator(root);
        }
        return root;

    }

    public TrueExecutionTimeRetriever getExecutionTimeRetriever(){
        return executionTimeRetriever;
    }

    /**
     * Performs a post order traversal of the tree to generate the accurate
     * traversal time
     */
    private void postOrderExecutionTimeGenerator(ExTree currentNode) {
        if(!currentNode.hasChildren()){
            //DO SOMETHING
            executionTimeRetriever.addMethod(currentNode.getMethod(),currentNode.getMethod().getOverallExecutionTime());
            return;
        }

        for(ExTree childNode: currentNode.getChildren()){
            postOrderExecutionTimeGenerator(childNode);
        }
        long currentExecutionTime = currentNode.getMethod().getOverallExecutionTime() - sumTimeOf(currentNode.getChildren());
        executionTimeRetriever.addMethod(currentNode.getMethod(),currentExecutionTime);




    }

    private long sumTimeOf(List<ExTree> children) {
        long overallTime = 0;
        for(ExTree tree: children){
            overallTime+=tree.getMethod().getOverallExecutionTime();
        }
        return overallTime;
    }

    private void buildExecutionTree(List<MethodBean> methods, int beginIndex, int endIndex, ExTree root) {

        if(Math.abs(endIndex - beginIndex) >= 2){
            Stack<MethodBean> methodsOnstack = new Stack<>();
            int currentBeginIndex = beginIndex;
            MethodBean opennerMethod = methods.get(currentBeginIndex);
            methodsOnstack.add(opennerMethod);
            boolean startOver = false;

            for( int i = beginIndex + 1; i <= endIndex; i++ ){
                MethodBean currentMethodBean = methods.get(i);

                if(startOver){
                    currentBeginIndex=i;
                    opennerMethod = methods.get(currentBeginIndex);
                }

                if(foundClosingMethod(methodsOnstack,opennerMethod,currentMethodBean)){
                    //pop from the stack
                    MethodBean poppedBean = methodsOnstack.pop();
                    assert (poppedBean.equals(opennerMethod));
                    ExTree newTree = new ConsTree(poppedBean);
                    root.addChild(newTree);
                    beginIndex = beginIndex + 1;
                    endIndex = i -1;
                    buildExecutionTree(methods,beginIndex,endIndex,newTree);
                    startOver = true;

                }else if(foundClosingMethod(methodsOnstack,methodsOnstack.peek(),currentMethodBean)){
                    methodsOnstack.pop();
                }
            }
        }
    }

    private boolean foundClosingMethod(Stack<MethodBean> methodsOnstack, MethodBean opennerMethod, MethodBean currentMethodBean) {
        return  methodsOnstack.peek().equals(opennerMethod) && opennerMethod.equals(currentMethodBean) && !currentMethodBean.isPushedOntoStack();
    }

    public ExecutionTreeBuilder withExecutionTimeGenerated(boolean performPostOrder) {
        //TODO
        this.performPostOrder = performPostOrder;
        return this;
    }
}
