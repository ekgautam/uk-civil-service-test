package com.ekansh.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@lombok.Data
@lombok.AllArgsConstructor
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InternalServerException(String msg) {
        super(msg);
    }

    public InternalServerException(Throwable e) {
        super(e);
    }
}
