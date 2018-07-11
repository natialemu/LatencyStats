import domain.LatencyStatsFacade;
import domain.abstraction.MethodAbs;
import domain.abstraction.ServiceAST;
import domain.builder.ExecutionTimeHelper;

public class MainClass {

    public static void main(String[] args){

        ExecutionTimeHelper helper = new ExecutionTimeHelper("testApp","123");
        ServiceAST service = helper.getTimeAdjustedService();

        LatencyStatsFacade latencyStats = new LatencyStatsFacade(service);

        System.out.println("Stats for service: " + service.getName() + "and request_i=123456");

        System.out.print("Slowest method is: " +latencyStats.getSlowestMethod());

        System.out.print("Slowest class is: " + latencyStats.getSlowestClass());

        System.out.print("The 3 slowest methods are: ");

        for(MethodAbs slowMethod: latencyStats.getNSlowestMethods(3)){
            System.out.println(slowMethod.getName()+ " execution time: " + slowMethod.getExecusionTime());
        }

    }
}
