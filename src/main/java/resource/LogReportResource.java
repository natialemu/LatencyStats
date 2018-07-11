package resource;

import Request.LatencyStatsRequest;
import Request.LatencyStatsRequestBuilder;
import activity.ReportLogActivity;
import domain.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/latency/logs")
@Api(value="Latency Stats", basePath = "/latency/stats", description="Latency statistics messages")
public class LogReportResource {

    private final ReportLogActivity reportLogActivity;

    @Autowired
    public LogReportResource(ReportLogActivity reportLogActivity){
        this.reportLogActivity = reportLogActivity;
    }

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

    }
    @RequestMapping(value = "/app", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?> createCompleteLogPerRequest(){
        /**
         * JSON data will be in the following structure:
         * a list of the following
         *     <Full method definition>:
         *              stackPushOrder:
         *              stackPopOrer:
         */

    }

    @RequestMapping(value = "/method/", consumes = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<?> changeMethodName(){

    }

    @RequestMapping(value = "/app", consumes = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<?> changeAppName(){

    }

    @RequestMapping(value = "/class", consumes = "application/json", method = RequestMethod.PUT)
    public ResponseEntity<?> changeClassName(){

    }
}
