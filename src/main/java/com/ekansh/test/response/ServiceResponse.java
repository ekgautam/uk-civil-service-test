package com.ekansh.test.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
public class ServiceResponse<T> extends ResponseEntity<T> {

    public ServiceResponse(T data) {
        super(data, HttpStatus.OK);
    }

    public ServiceResponse(T data, HttpStatus status) {
        super(data, status);
    }

}
