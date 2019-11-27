package com.ekansh.test.response;

import java.util.ArrayList;

import com.ekansh.test.utils.TestUtility;
import org.apache.commons.collections.CollectionUtils;
import java.util.List;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@lombok.Data
public class ValidationResponse {

    private Boolean isValid;

    private List<String> errors = new ArrayList<>();

    public String getErrorMessage() {
        if(CollectionUtils.isNotEmpty(errors)) {
            String errorId = TestUtility.getUniqueErrorId();
            StringBuilder sb = new StringBuilder("Error Id: ").
                    append(errorId).
                    append(", validations failed: ").
                    append(errors.toString());
            return sb.toString();
        } else {
            return null;
        }
    }



}
