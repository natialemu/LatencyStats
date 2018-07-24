package com.latency.stats.domain;

import com.latency.stats.dataaccess.LatencyDAO;
import com.latency.stats.dataaccess.entity.MethodEntity;
import com.latency.stats.domain.abstraction.*;

import java.util.*;

public class LatencyStatsFacade {

    private ServiceAST service;
    private LatencyDAO latencyDAO;
    private Map<ServiceAST,Boolean> criticalComponents;
    private List<MethodAbs> criticalMethods;
    private List<ClassAbs> criticalClasses;
    private List<PackageAbs> criticalPackages;
    private List<MethodAbs> slowestMethods;
    private long requestID;

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public LatencyStatsFacade(ServiceAST service, long requestID){
        this.service = service;
        latencyDAO = new LatencyDAO();
        criticalComponents = new HashMap<>();
        this.requestID = requestID;

        criticalClasses = new ArrayList<>();
        criticalMethods = new ArrayList<>();
        criticalPackages = new ArrayList<>();
        generateCriticalComponents();

    }

    public LatencyStatsFacade(long requestID){

        latencyDAO = new LatencyDAO();
        criticalComponents = new HashMap<>();
        this.requestID = requestID;

        criticalClasses = new ArrayList<>();
        criticalMethods = new ArrayList<>();
        criticalPackages = new ArrayList<>();
        generateCriticalComponents();

    }

    public Map<ServiceAST, Boolean> getCriticalComponents() {
        return criticalComponents;
    }

    public void setCriticalComponents(Map<ServiceAST, Boolean> criticalComponents) {
        this.criticalComponents = criticalComponents;
    }

    public LatencyDAO getLatencyDAO() {
        return latencyDAO;
    }

    public void setLatencyDAO(LatencyDAO latencyDAO) {
        this.latencyDAO = latencyDAO;
    }

    public ServiceAST getService() {

        return service;
    }

    public void setService(ServiceAST service) {
        this.service = service;
    }



    private void generateCriticalComponents() {
        generateCriticalComponents(service);
    }

    private void generateCriticalComponents(ServiceAST root) {
        if(root instanceof MethodAbs && methodIsCritical((MethodAbs)root)){
            criticalComponents.put(root,true);
            criticalMethods.add((MethodAbs)root);
        }else if(root instanceof MethodAbs){
            slowestMethods.add((MethodAbs)root);

        }
        else{
            boolean isCritical = false;
            for(ServiceAST child: root.getChildren()){
                generateCriticalComponents(child);
                if(criticalComponents.containsKey(child)){
                    isCritical = true;
                }
            }
            if(isCritical){
                criticalComponents.put(root,true);
                if(root instanceof MethodAbs){
                    criticalMethods.add((MethodAbs) root);
                }else if(root instanceof ClassAbs){
                    criticalClasses.add((ClassAbs) root);

                }else if(root instanceof PackageAbs){
                    criticalPackages.add((PackageAbs) root);

                }
            }
        }

    }

    private boolean methodIsCritical(MethodAbs root) {

        long executionTime = root.getExecusionTime();
        long avgExecutionTime = latencyDAO.getAvgExecutionTime((MethodAbs)root,requestID);
        if(executionTime > avgExecutionTime){
            return true;
        }
        return false;
    }

    public List<MethodAbs> getNSlowestMethods(int n) {
        Collections.sort(slowestMethods);
        List<MethodAbs> nSlowestMethods = new ArrayList<>();
        for(int i =slowestMethods.size() - 1 ; i >= n; i--){
            nSlowestMethods.add(slowestMethods.get(i));
        }
        return nSlowestMethods;
    }


    public void persistMethod(MethodEntity methodEntity){
        LatencyDAO latencyDAO = new LatencyDAO();
        latencyDAO.saveMethod(methodEntity);
    }
    public List<MethodAbs> getUnderPerformingMethods(int i) {
        return criticalMethods;
    }

    public List<ClassAbs> getUnderPerformingClasses(int i) {
        return criticalClasses;
    }

    public List<PackageAbs> getUnderPerformingPackages(int i) {
        return criticalPackages;
    }
}
