package com.savonik.employeedb.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse {

    public final String status;
    public final Object response;

    public RestResponse(String status, Object response) {
        this.status = status;
        this.response = response;
    }

    public RestResponse(String status) {
        this.status = status;
        this.response = null;
    }
}
