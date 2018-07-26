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
        slowestMethods = new ArrayList<>();
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
            criticalComponents.put(root,true); // for dp purposes
            criticalMethods.add((MethodAbs)root);
        }else if(root instanceof MethodAbs){
            slowestMethods.add((MethodAbs)root);

        }
        else{
            boolean isCritical = false;
            for(ServiceAST child: root.getChildren()){
                generateCriticalComponents(child);
                if(criticalComponents.containsKey(child)){ // if at least one child is a critical component
                    isCritical = true;
                }
            }
            if(isCritical){
                criticalComponents.put(root,true);
                if(root instanceof MethodAbs){//TODO: remove this. This should not be possible
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
        long avgExecutionTime = latencyDAO.getAvgExecutionTime(root,requestID);
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
        Collections.sort(criticalMethods);
        List<MethodAbs> nCriticalMethods = new ArrayList<>();
        int numMethodsToReturn = (int)((i/100.0)*criticalMethods.size());
        for(int j =criticalMethods.size() - 1 ; j >= numMethodsToReturn; j--){
            nCriticalMethods.add(slowestMethods.get(i));
        }

        return nCriticalMethods;
    }

    public List<ClassAbs> getUnderPerformingClasses(int i) {
        return criticalClasses;//TODO: returns all under-performing classes. fix it
    }

    public List<PackageAbs> getUnderPerformingPackages(int i) {
        return criticalPackages;//TODO: returns all under-performing packages. fix it
    }
}
