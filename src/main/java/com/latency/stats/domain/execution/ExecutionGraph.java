package com.latency.stats.domain.execution;

import com.latency.stats.dal.LatencyDAO;

public class ExecutionGraph {

    private ExTree root;
    private LatencyDAO latencyDAO;
    private String requestID;
    private String appName;

    public ExecutionGraph(String requestID, String appName){
        root = new Empty();
        this.requestID = requestID;
        this.appName = appName;
    }


//    public ExTree buildAndRetrieveExecutionTree(){
//
//        List<MethodBean> methods = latencyDAO.getOrderedMethods(requestID,appName);
//        assert(methods.size() > 1);
//        MethodBean rootMethod = methods.get(0);
//        root = new ConsTree(rootMethod);
//        buildExecutionTree(methods,1,methods.size()-2,root);
//        return root;
//
//    }
//
//    private void buildExecutionTree(List<MethodBean> methods, int beginIndex, int endIndex, ExTree root) {
//        if(Math.abs(endIndex - beginIndex) >= 2){
//            Stack<MethodBean> methodsOnstack = new Stack<>();
//            int currentBeginIndex = beginIndex;
//            MethodBean opennerMethod = methods.get(currentBeginIndex);
//            methodsOnstack.add(opennerMethod);
//            boolean startOver = false;
//
//            for( int i = beginIndex + 1; i <= endIndex; i++ ){
//                MethodBean currentMethodBean = methods.get(i);
//
//                if(startOver){
//                    currentBeginIndex=i;
//                    opennerMethod = methods.get(currentBeginIndex);
//                }
//
//                if(foundClosingMethod(methodsOnstack,opennerMethod,currentMethodBean)){
//                    //pop from the stack
//                    MethodBean poppedBean = methodsOnstack.pop();
//                    assert (poppedBean.equals(opennerMethod));
//                    ExTree newTree = new ConsTree(poppedBean);
//                    root.addChild(newTree);
//                    beginIndex = beginIndex + 1;
//                    endIndex = i -1;
//                    buildExecutionTree(methods,beginIndex,endIndex,newTree);
//                    startOver = true;
//
//                }else if(foundClosingMethod(methodsOnstack,methodsOnstack.peek(),currentMethodBean)){
//                    methodsOnstack.pop();
//                }
//            }
//        }
//    }
//
//    private boolean foundClosingMethod(Stack<MethodBean> methodsOnstack, MethodBean opennerMethod, MethodBean currentMethodBean) {
//        return  methodsOnstack.peek().equals(opennerMethod) && opennerMethod.equals(currentMethodBean) && !currentMethodBean.isPushedOntoStack();
//    }
//
//
////    private Map<String, List<String>> graph;
////
////    public ExecutionGraph(){
////        graph = new HashMap<>();
////    }
////
////    public List<String> getMethodsCalledBy(String methodName){
////        MethodAbs method = new MethodAbs();
////        method.setName(methodName);
////        return getMethodsCalledBy(method);
//    }
//
//    public List<String> getMethodsCalledBy(MethodAbs method){
//        if(graph.containsKey(method)){
//            return graph.get(method);
//        }
//        return null;
//    }
//
//    public void addMethodCall(String caller, String callee){
//        if(graph.containsKey(caller)){
//            List<String> methods = graph.get(caller);
//            methods.add(callee);
//            graph.put(caller,methods);
//        }else{
//            List<String> methods = new ArrayList<>();
//            methods.add(callee);
//            graph.put(caller,methods);
//        }
//    }
//
//    public void addMethod(String method){
//        //TODO
//    }
//
//    public boolean methodWasNeverCalled(String method){
//        assert (methodExistsInGraph(method));
//        for(String methodAbs: graph.keySet()){
//            if(graph.get(methodAbs).contains(method)){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean methodExistsInGraph(String method){
//        return graph.containsKey(method);
//    }

}
