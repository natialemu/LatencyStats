package com.latency.stats.service.resource;

import com.latency.stats.service.representation.request.methodlog.MethodLogRequest;
import com.latency.stats.service.representation.request.methodlog.MethodLogRequestBuilder;
import com.latency.stats.service.representation.request.stats.LatencyStatsRequest;
import com.latency.stats.service.representation.request.stats.LatencyStatsRequestBuilder;
import com.latency.stats.service.activity.ReportLogActivity;
import com.latency.stats.domain.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.latency.stats.validation.GetRequestValidator;

@RestController
@RequestMapping("/latency/logs")
@Api(value="Latency Stats", basePath = "/latency/stats", description="Latency statistics messages")
public class LogReportResource {

    private final GetRequestValidator getRequestValidator;

    private final ReportLogActivity reportLogActivity;

    @Autowired
    public LogReportResource(ReportLogActivity reportLogActivity, GetRequestValidator getRequestValidator){
        this.reportLogActivity = reportLogActivity;
        this.getRequestValidator = getRequestValidator;
    }

    @ApiResponses(value={@ApiResponse(code=200, message="")})
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getGeneralReport(
            @RequestHeader(value = Constants.HTTP_HEADER_RETURN_SUCCESS_FOR_MISSING_REPORT, required = false) boolean returnSuccessForMissingProperties,
            @ApiParam(value = Constants.APP_ID, required = true) @PathVariable(Constants.APP_ID) String appName,
            @ApiParam(value = Constants.REQUEST_ID) @PathVariable(value = Constants.REQUEST_ID) String requestID

            ){

        LatencyStatsRequest request = LatencyStatsRequestBuilder.buildRequest(appName,requestID,returnSuccessForMissingProperties);
        validateRequest(request);




        return reportLogActivity.getGeneralReport(request);


    }

    private void validateRequest(LatencyStatsRequest request) {

        //getRequestValidator.accept(request);
    }


    @RequestMapping(value = "/fullReport" + Constants.REQUEST_ID + "}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getExhaustiveReport(

            @RequestHeader(value = Constants.HTTP_HEADER_RETURN_SUCCESS_FOR_MISSING_REPORT, required = false) boolean returnSuccessForMissingProperties,
            @ApiParam(value = Constants.APP_ID, required = true) @PathVariable(Constants.APP_ID) String appName,
            @ApiParam(value = Constants.REQUEST_ID) @PathVariable(value = Constants.REQUEST_ID) String requestID
    ){

        LatencyStatsRequest request = LatencyStatsRequestBuilder.buildRequest(appName,requestID,returnSuccessForMissingProperties);
        validateRequest(request);

        return reportLogActivity.getExhaustiveReport(request);






    }

    @RequestMapping(value = "/full", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> createFullAppLog(){
        return null;

    }
    @RequestMapping(value = "/{appname}/{requestid}", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> createCompleteLogPerRequest(
            @PathVariable("appname") String appName,
            @PathVariable("requestid") String requestId,
            @RequestParam("methodname") String fullBeforeMethodName,
            @RequestParam("starttime") long calledMethodStartTime,
            @RequestParam("endtime") long calledMethodEndTime,
            @RequestParam("startrank") int stackPushRank,
            @RequestParam("endrank") int stackPopRank

    ){

        MethodLogRequest request = MethodLogRequestBuilder.buildRequest(appName,requestId,fullBeforeMethodName,calledMethodStartTime,calledMethodEndTime,
                stackPushRank,stackPopRank);

        validateAndProcessLogRequest(request);


        /**
         * JSON data will be in the following structure:
         * a list of the following
         *     <Full method definition>:
         *              stackPushOrder:
         *
         *
         *              @PathVariable("appname") String appName,
         *                                      @PathVariable("requestid") String requestId,
         *                                      @RequestParam("methodname") String fullBeforeMethodName,
         *                                      @RequestParam("starttime") long calledMethodStartTime,
         *                                      @RequestParam("endtime") long calledMethodEndTime,
         *                                      @RequestParam("startrank") int stackPushRank,
         *                                      @RequestParam("endrank") int stackPopRank
         */

        return null;
    }

    private void validateAndProcessLogRequest(MethodLogRequest request) {
        //call appropriate method

        reportLogActivity.processMethodLogRequest(request);
    }

    @RequestMapping(value = "/method/", consumes = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<?> changeMethodName(){
        return null;

    }

    @RequestMapping(value = "/app", consumes = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<?> changeAppName(){
        return null;

    }

    @RequestMapping(value = "/class", consumes = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<?> changeClassName(){
        return null;

    }
}
