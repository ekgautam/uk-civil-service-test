package com.ekansh.test.exception;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */

import com.ekansh.test.response.BaseObjectResponse;
import com.ekansh.test.response.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Base exception handler handles all exception that has been thrown by application and handled exception is transformed
 * to a generic message and returned to controller.
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ServiceResponse<BaseObjectResponse<String>> handleUnprocessableEntityError(UnprocessableEntityException ex) {
        return new ServiceResponse<>(new BaseObjectResponse<>(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Unprocessable Entity", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TimeoutException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ResponseBody
    public ServiceResponse<BaseObjectResponse<String>> handleTimeoutError(TimeoutException ex) {
        return new ServiceResponse<>(new BaseObjectResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Gateway Timeout Exception", ex.getMessage()), HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ServiceResponse<BaseObjectResponse<String>> handleServerError(InternalServerException ex) {
        return new ServiceResponse<>(new BaseObjectResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Exception", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

