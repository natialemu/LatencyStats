package domain.abstraction;

import java.util.List;

public class PackageAbs implements ServiceAST {

    private List<ServiceAST> abstractions;
    private String packageName;
    private long executionTime;

    @Override
    public void add(ServiceAST abstraction) {
        abstractions.add(abstraction);

    }

    @Override
    public void getAbstractionInformation() {

    }

    @Override
    public long getExecusionTime() {
        return executionTime;
    }

    @Override
    public int getSize() {
        return abstractions.size();
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
        return packageName;
    }

    @Override
    public void setName(String name) {
        this.packageName = name;
    }

    @Override
    public boolean contains(ServiceAST serviceAST) {
        return abstractions.contains(serviceAST);
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
        return null;
    }

    @Override
    public void setExecutionTime(long totalTime) {
        executionTime = totalTime;
    }
}
