package activity;

import Request.LatencyStatsRequest;
import domain.abstraction.ServiceAST;
import domain.LatencyStatsFacade;
import domain.builder.ExecutionTimeHelper;
import org.springframework.http.ResponseEntity;

public class ReportLogActivity {

    public ResponseEntity<?> getGeneralReport(LatencyStatsRequest request) {

        String appName = request.getBody().getAppName();
        String requestId = request.getBody().getAppName();

        ExecutionTimeHelper helper = new ExecutionTimeHelper(appName,requestId);
        ServiceAST service = helper.getTimeAdjustedService();

        LatencyStatsFacade latencyStats = new LatencyStatsFacade(service);

        //build a response out of results from the facade

        return null;
    }

    public ResponseEntity<?> getExhaustiveReport(LatencyStatsRequest request) {
        return null;
    }
    /**
     *
     * This class will send the right info
     * to the builders so that they can build
     * the graph and run them
     *
     * This class will also send to the right class
     * for querying the graphs once it is build
     *
     * it will then return the results to the caller
     *
     */
}
