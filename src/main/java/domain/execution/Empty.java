package domain.execution;

import domain.MethodBean;
import domain.builder.ExecutionTreeBuilder;

import java.util.List;

public class Empty implements ExTree {

    public Empty(){
        //TODO
    }
    @Override
    public List<ExTree> getChildren() {
        return null;
    }

    @Override
    public MethodBean getMethod() {
        return null;
    }

    @Override
    public void addChild(ExTree tree) {

    }

    @Override
    public boolean hasChildren() {
        return false;
    }


}
