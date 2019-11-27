package com.ekansh.test.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseObjectResponse<T> extends AbstractResponse {

    private T data;

    public BaseObjectResponse(T data) {
        super();
        this.data = data;
    }

    public BaseObjectResponse() {
        super();
    }

    public BaseObjectResponse(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }

}