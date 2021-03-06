package com.latency.stats.service.activity;

import com.latency.stats.dataaccess.entity.MethodEntity;
import com.latency.stats.domain.abstraction.ClassAbs;
import com.latency.stats.domain.abstraction.PackageAbs;
import com.latency.stats.service.representation.request.methodlog.MethodLogRequest;
import com.latency.stats.service.representation.request.stats.LatencyStatsRequest;
import com.latency.stats.domain.abstraction.MethodAbs;
import com.latency.stats.domain.abstraction.ServiceAST;
import com.latency.stats.domain.LatencyStatsFacade;
import com.latency.stats.domain.builder.ExecutionTimeHelper;
import com.latency.stats.service.representation.response.stats.mini.ClassStatsMini;
import com.latency.stats.service.representation.response.stats.mini.PackageStatsMini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.latency.stats.service.representation.response.GeneralReport;
import com.latency.stats.service.representation.response.stats.mini.MethodStatsMini;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportLogActivity {

    @Autowired
    private ExecutionTimeHelper executionTimeHelper;

    @Autowired
    private LatencyStatsFacade latencyStatsFacade;


    public ResponseEntity<?> getGeneralReport(LatencyStatsRequest request) {


        ServiceAST service = executionTimeHelper.getService(request);
        latencyStatsFacade.setRequestID(Long.parseLong(request.getBody().getRequestID()));
        latencyStatsFacade.generateCriticalComponents(service);

        GeneralReport generalReport = new GeneralReport();

        generalReport.setRequestID(request.getBody().getRequestID());
        setClassStats(generalReport,latencyStatsFacade);
        setMethodStats(generalReport,latencyStatsFacade);
        setPackageStats(generalReport,latencyStatsFacade);

        return ResponseEntity.ok(generalReport);
    }

    private void setPackageStats(GeneralReport generalReport, LatencyStatsFacade statsFacade) {
        PackageStatsMini packageStatsMini  = new PackageStatsMini();
        List<PackageAbs> criticalPackages = statsFacade.getUnderPerformingPackages(10);


        for(PackageAbs packagee: criticalPackages){
            packageStatsMini.addCriticalPackages(packagee.getName(),packagee.getExecusionTime());
        }
        generalReport.setPackageStats(packageStatsMini);

    }

    private void setMethodStats(GeneralReport generalReport, LatencyStatsFacade statsFacade) {
        MethodStatsMini methodStatsMini  = new MethodStatsMini();
        List<MethodAbs> slowestMethods = statsFacade.getNSlowestMethods(10);//10 percent
        List<MethodAbs> criticalMethods = statsFacade.getUnderPerformingMethods(10);

        for(MethodAbs method: slowestMethods){
            methodStatsMini.addSlowMethod(method.getName(),method.getExecusionTime());
        }
        for(MethodAbs method: criticalMethods){
            methodStatsMini.addCriticalMethods(method.getName(),method.getExecusionTime());
        }
        generalReport.setMethodStats(methodStatsMini);


    }

    private void setClassStats(GeneralReport generalReport, LatencyStatsFacade statsFacade) {
        ClassStatsMini classStatsMini = new ClassStatsMini();

        List<ClassAbs> criticalClasses = statsFacade.getUnderPerformingClasses(10);

        for(ClassAbs clazz: criticalClasses){
            classStatsMini.addCriticalClasses(clazz.getName(),clazz.getExecusionTime());
        }
        generalReport.setClassStats(classStatsMini);

    }

    public ResponseEntity<?> getExhaustiveReport(LatencyStatsRequest request) {



        return null;
    }

    public void processMethodLogRequest(MethodLogRequest request) {
        /**
         * Use options to set these values only if they are not null or -1
         */
        String appName = request.getMethodLogRequestBody().getAppName();
        String requestId = request.getMethodLogRequestBody().getRequestId();
        int stackPushRank = request.getMethodLogRequestBody().getPushStackRank();
        int stackPopRank = request.getMethodLogRequestBody().getPopStackRank();
        Long stackPushTime = request.getMethodLogRequestBody().getCalledMethodStartTime();
        Long stackPopTime = request.getMethodLogRequestBody().getCalledMethodEndtime();
        String methodName = request.getMethodLogRequestBody().getFullBeforeMethodName();

        MethodEntity entity = new MethodEntity(Long.parseLong(requestId),appName,methodName,stackPopRank,stackPushRank,stackPopTime,stackPushTime);

        latencyStatsFacade.persistMethod(entity);
    }

}
