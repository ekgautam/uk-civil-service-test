package com.ekansh.test.requestvalidators;

import com.ekansh.test.response.ValidationResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@Component
public class UsersRequestValidation {

    /**
     * This validates the get request and request isn't served if it fails. Appropriate message is returned along with
     * the errors/validation which has failed.
     */
    public ValidationResponse validateRequest(String city, Double milesWithin) {

        ValidationResponse response = new ValidationResponse();

        if (StringUtils.isEmpty(city) && milesWithin == null) {
            response.getErrors().add("City is empty or invalid and miles within is also null");
        }

        if (!CollectionUtils.isEmpty(response.getErrors())) {
            response.setIsValid(false);
        } else {
            response.setIsValid(true);
        }

        return response;

    }
}
