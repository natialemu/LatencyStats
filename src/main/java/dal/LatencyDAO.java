package dal;

import domain.MethodBean;

import java.util.List;

public interface LatencyDAO {

    List<MethodBean> getOrderedMethods(String requestID, String appName );

}
