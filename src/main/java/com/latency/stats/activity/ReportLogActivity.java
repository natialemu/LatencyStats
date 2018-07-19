package com.latency.stats.activity;

import com.latency.stats.Request.LatencyStatsRequest;
import com.latency.stats.domain.abstraction.MethodAbs;
import com.latency.stats.domain.abstraction.ServiceAST;
import com.latency.stats.domain.LatencyStatsFacade;
import com.latency.stats.domain.builder.ExecutionTimeHelper;
import org.springframework.http.ResponseEntity;
import com.latency.stats.representation.GeneralReport;
import com.latency.stats.representation.stats.mini.MethodStatsMini;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportLogActivity {

    public ResponseEntity<?> getGeneralReport(LatencyStatsRequest request) {

        //build a response out of results from the facade
        LatencyStatsFacade statsFacade = getLatencyStatsFacade(request);

        GeneralReport generalReport = new GeneralReport();

        generalReport.setRequestID(request.getBody().getRequestID());
        setClassStats(generalReport,statsFacade);
        setMethodStats(generalReport,statsFacade);
        setPackageStats(generalReport,statsFacade);

        return ResponseEntity.ok(generalReport);
    }

    private void setPackageStats(GeneralReport generalReport, LatencyStatsFacade statsFacade) {

    }

    private void setMethodStats(GeneralReport generalReport, LatencyStatsFacade statsFacade) {
        MethodStatsMini methodStatsMini  = new MethodStatsMini();
        List<MethodAbs> methods = statsFacade.getNSlowestMethods(10);//10 percent

        //TODO: how do you get the critical methods


    }

    private void setClassStats(GeneralReport generalReport, LatencyStatsFacade statsFacade) {

    }

    public LatencyStatsFacade getLatencyStatsFacade(LatencyStatsRequest request){

        String appName = request.getBody().getAppName();
        String requestId = request.getBody().getAppName();

        ExecutionTimeHelper helper = new ExecutionTimeHelper(appName,requestId);
        ServiceAST service = helper.getService();

        LatencyStatsFacade latencyStats = new LatencyStatsFacade(service);
        return latencyStats;




    }

    public ResponseEntity<?> getExhaustiveReport(LatencyStatsRequest request) {

        LatencyStatsFacade statsFacade = getLatencyStatsFacade(request);

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
