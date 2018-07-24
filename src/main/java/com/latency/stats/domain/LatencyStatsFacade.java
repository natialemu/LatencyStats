package com.latency.stats.domain;

import com.latency.stats.dataaccess.LatencyDAO;
import com.latency.stats.dataaccess.entity.MethodEntity;
import com.latency.stats.domain.abstraction.*;

import java.util.List;

public class LatencyStatsFacade {

    private ServiceAST service;
    private LatencyDAO latencyDAO;


    public LatencyStatsFacade(ServiceAST service){
        this.service = service;

    }

    public LatencyStatsFacade(){
        this.service = service;

    }

    public List<MethodAbs> getNSlowestMethods(int n) {
        return null;
    }

    public List<ClassAbs> getNSlowestClass(int n) {
        return null;
    }


    public void persistMethod(MethodEntity methodEntity){
        LatencyDAO latencyDAO = new LatencyDAO();
        latencyDAO.saveMethod(methodEntity);
    }


    /**
     * There should be a way of returning result of all three methods below after one traversal of service on the way back
     * @param i
     * @return
     */
    public List<MethodAbs> getUnderPerformingMethods(int i) {


        //TODO
        //Get 10 perc of methods that are performing lower than the average performace for that particular method in that app
        /**
         * go through the service:
         *   when you reach a method:
         *     ask dao to get that method info from db for avg performace
         *     if so add it
         *
         */
        return null;

    }

    public List<ClassAbs> getUnderPerformingClasses(int i) {
        /**
         * TODO
         * if a class contains any of the underperforming methods then send it back
         */
        return null;
    }

    public List<PackageAbs> getUnderPerformingPackages(int i) {
        /**
         * TODO if package contains any of the underperforming classes then send it back
         *
         *
         */
        return null;
    }
}
