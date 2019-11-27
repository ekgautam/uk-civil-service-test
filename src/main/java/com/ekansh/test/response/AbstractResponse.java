package com.ekansh.test.response;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
public abstract class AbstractResponse {

    private Integer code;
    private String message;

    public AbstractResponse() {
        this.code = 200;
        this.message = "OK";
    }

    public AbstractResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public AbstractResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public AbstractResponse setMessage(String message) {
        this.message = message;
        return this;
    }

}
