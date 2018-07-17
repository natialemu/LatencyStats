package domain.builder;

import dal.LatencyDAO;
import dal.LatencyDaoImpl;
import domain.MethodBean;
import domain.execution.ConsTree;
import domain.execution.Empty;
import domain.execution.ExTree;

import java.util.List;
import java.util.Stack;

public class ExecutionTreeBuilder {

    private ExTree root;
    private LatencyDAO latencyDAO;
    private String requestID;
    private String appName;

    public ExecutionTreeBuilder(){
        root = new Empty();
        latencyDAO = new LatencyDaoImpl();
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
        return root;

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
}
