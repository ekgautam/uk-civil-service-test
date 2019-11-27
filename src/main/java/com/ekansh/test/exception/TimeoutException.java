package com.ekansh.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@lombok.Data
@lombok.AllArgsConstructor
@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
public class TimeoutException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TimeoutException(String msg) {
        super(msg);
    }

}
