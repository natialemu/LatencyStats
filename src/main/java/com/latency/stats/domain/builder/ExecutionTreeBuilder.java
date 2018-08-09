package com.latency.stats.domain.builder;

import com.latency.stats.dataaccess.LatencyDAO;
import com.latency.stats.domain.MethodBean;
import com.latency.stats.domain.execution.ConsTree;
import com.latency.stats.domain.execution.Empty;
import com.latency.stats.domain.execution.ExTree;
import com.latency.stats.domain.execution.TrueExecutionTimeRetriever;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Stack;

public class ExecutionTreeBuilder {

    @Autowired
    private LatencyDAO latencyDAO;

    private static ExTree root;

    private String requestID;
    private String appName;
    private boolean performPostOrder;

    public ExecutionTreeBuilder(){
        root = new Empty();
    }

    public ExecutionTreeBuilder withRequestId(String requestID){
        this.requestID = requestID;
        return this;
    }

    public ExecutionTreeBuilder withAppName(String appName){
        this.appName = appName;
        return this;
    }


    public void generateExecutionTimeRetriver(){

        List<MethodBean> methods = latencyDAO.getOrderedMethods(requestID,appName);
        assert(methods.size() > 1);//for every openning method there needs to be a closing one
        MethodBean rootMethod = methods.get(0);
        root = new ConsTree(rootMethod);
        buildExecutionTree(methods,1,methods.size()-2,root);
        if(performPostOrder){
            postOrderExecutionTimeGenerator(root);
        }

    }


    /**
     * Performs a post order traversal of the tree to generate the accurate
     * traversal time
     */
    private void postOrderExecutionTimeGenerator(ExTree currentNode) {
        if(!currentNode.hasChildren()){
            //DO SOMETHING
            TrueExecutionTimeRetriever.addMethod(currentNode.getMethod(),currentNode.getMethod().getOverallExecutionTime());
            return;
        }

        for(ExTree childNode: currentNode.getChildren()){
            postOrderExecutionTimeGenerator(childNode);
        }
        long currentExecutionTime = currentNode.getMethod().getOverallExecutionTime() - sumTimeOf(currentNode.getChildren());
        TrueExecutionTimeRetriever.addMethod(currentNode.getMethod(),currentExecutionTime);




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
            boolean startOver = false; //to indicate completion of parsing the first method parenthesis in this range

            for( int i = beginIndex + 1; i <= endIndex; i++ ){//parse all methods in range [beginIndex,endIndex]
                MethodBean currentMethodBean = methods.get(i);

                if(startOver){
                    currentBeginIndex=i;
                    opennerMethod = methods.get(currentBeginIndex);
                }

                //found the corresponding closer method for opennerMethod which is currentMethodBean
                if(foundClosingMethod(methodsOnstack,opennerMethod,currentMethodBean)){
                    //pop from the stack
                    MethodBean poppedBean = methodsOnstack.pop();
                    assert (poppedBean.equals(opennerMethod));
                    ExTree newTree = new ConsTree(poppedBean);
                    root.addChild(newTree);
                    int newBeginIndex = currentBeginIndex + 1;
                    endIndex = i -1;
                    buildExecutionTree(methods,newBeginIndex,endIndex,newTree);
                    startOver = true;

                }else if(foundClosingMethod(methodsOnstack,methodsOnstack.peek(),currentMethodBean)){
                    methodsOnstack.pop(); //found a closing method to some other method than opennerMethod
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
