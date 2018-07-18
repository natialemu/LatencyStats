package domain.abstraction;

import domain.builder.AstBuilder;

import java.util.List;

public interface ServiceAST {
    void add(ServiceAST abstraction);
    void getAbstractionInformation();//adjust the return type
    long getExecusionTime();
    int getSize();
    MethodAbs getSlowestMethod();
    List<MethodAbs> getNSlowestMethods(int n);
    List<ClassAbs> getNSlowestClass(int n);
    ClassAbs getSlowestClass();
    ApplicationAbs getSlowestApplication();
    String getName();
    void setName(String name);
    boolean contains(ServiceAST serviceAST);
    void addAsChildOf(ServiceAST parent, ServiceAST child);
    List<ServiceAST> getChildren();

    MethodAbs getMethod(String calledMethodName);

    void setExecutionTime(long totalTime);

    static AstBuilder builder(){
        return new AstBuilder();
    }
}
