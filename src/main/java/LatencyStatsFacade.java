import domain.abstraction.ApplicationAbs;
import domain.abstraction.ClassAbs;
import domain.abstraction.MethodAbs;
import domain.abstraction.ServiceAST;

import java.util.List;

public class LatencyStatsFacade {

    private ServiceAST service;

    public LatencyStatsFacade(ServiceAST service){
        this.service = service;

    }


    public MethodAbs getSlowestMethod() { return null; }

    public List<MethodAbs> getNSlowestMethods(int n) {
        return null;
    }

    public List<ClassAbs> getNSlowestClass(int n) {
        return null;
    }

    public ClassAbs getSlowestClass() {
        return null;
    }

    public ApplicationAbs getSlowestApplication() {
        return null;
    }
}
