package com.ekansh.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@lombok.Data
@lombok.AllArgsConstructor
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
    private static final long serialVersionUID = 1370400556986275958L;

    public UnprocessableEntityException(String message) {
        super(message);
    }
}
