package com.latency.stats.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


public enum GetLatencyStatsError {
    APP_ID_NOT_FOUND(BAD_REQUEST,"com.latency.stats.Application id was not found");

    private final HttpStatus status;
    private final String description;


    GetLatencyStatsError(HttpStatus status, String description){
        this.status = status;
        this.description = description;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

}
