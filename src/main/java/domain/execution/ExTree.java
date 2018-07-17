package domain.execution;

import domain.MethodBean;
import domain.builder.ExecutionTreeBuilder;

import java.util.List;

public interface ExTree {

    List<ExTree> getChildren();

    MethodBean getMethod();

    void addChild(ExTree tree);

    static ExecutionTreeBuilder builder(){
        return new ExecutionTreeBuilder();
    }



}
