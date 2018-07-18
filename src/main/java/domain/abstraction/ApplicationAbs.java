package domain.abstraction;

import java.util.List;

public class ApplicationAbs implements ServiceAST {

    private List<ServiceAST> abstractions;
    private String applicationName;
    private int executionTime;

    public ApplicationAbs(String applicationName){
        this.applicationName = applicationName;

    }
    public ApplicationAbs(String applicationName, int executionTime){
        this.applicationName = applicationName;
        this.executionTime = executionTime;

    }



    @Override
    public void add(ServiceAST abstraction) {

    }

    @Override
    public void getAbstractionInformation() {

    }

    @Override
    public int getExecusionTime() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public MethodAbs getSlowestMethod() {
        return null;
    }

    @Override
    public List<MethodAbs> getNSlowestMethods(int n) {
        return null;
    }

    @Override
    public List<ClassAbs> getNSlowestClass(int n) {
        return null;
    }

    @Override
    public ClassAbs getSlowestClass() {
        return null;
    }

    @Override
    public ApplicationAbs getSlowestApplication() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public boolean contains(ServiceAST serviceAST) {
        return false;
    }

    @Override
    public void addAsChildOf(ServiceAST parent, ServiceAST child) {

    }

    @Override
    public List<ServiceAST> getChildren() {
        return abstractions;
    }

    @Override
    public MethodAbs getMethod(String calledMethodName) {
        //TODO
        String[] methodSignature = calledMethodName.split(".");
        for(ServiceAST serviceAST: abstractions){
//            if(serviceAST.getName().equals(metho))
        }

        return null;
    }

    @Override
    public void setExecutionTime(int totalTime) {

        executionTime = totalTime;
    }
}
