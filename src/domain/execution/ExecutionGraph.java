package domain.execution;

import domain.abstraction.MethodAbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecutionGraph {

    private Map<String, List<String>> graph;

    public ExecutionGraph(){
        graph = new HashMap<>();
    }

    public List<String> getMethodsCalledBy(String methodName){
        MethodAbs method = new MethodAbs();
        method.setName(methodName);
        return getMethodsCalledBy(method);
    }

    public List<String> getMethodsCalledBy(MethodAbs method){
        if(graph.containsKey(method)){
            return graph.get(method);
        }
        return null;
    }

    public void addMethodCall(String caller, String callee){
        if(graph.containsKey(caller)){
            List<String> methods = graph.get(caller);
            methods.add(callee);
            graph.put(caller,methods);
        }else{
            List<String> methods = new ArrayList<>();
            methods.add(callee);
            graph.put(caller,methods);
        }
    }

    public void addMethod(String method){
        //TODO
    }

    public boolean methodWasNeverCalled(String method){
        assert (methodExistsInGraph(method));
        for(String methodAbs: graph.keySet()){
            if(graph.get(methodAbs).contains(method)){
                return false;
            }
        }
        return true;
    }

    public boolean methodExistsInGraph(String method){
        return graph.containsKey(method);
    }

}
