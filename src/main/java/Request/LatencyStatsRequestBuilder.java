package Request;

import java.util.Optional;

public class LatencyStatsRequestBuilder {

    private LatencyStatsRequestBuilder() {

    }

    public static LatencyStatsRequest buildRequest(String requestID, String appName, boolean returnTrueForMissingReport) {
        return LatencyStatsRequest.builder()
                .body(new LatencyStatsRequestBody(requestID,appName))
                .filters(buildFilter())
                .headers(buildHeader(returnTrueForMissingReport))
                .buildRequest();
    }

    public static Filters buildFilter() {
        return Filters.builder()
                .build();
    }

    private static Headers buildHeader(Boolean returnSuccessForMissingReport) {
        return Headers.builder()
                .returnSuccessForMissingProperties(returnSuccessForMissingReport == null ? false : returnSuccessForMissingReport)
                .build();
    }
}
