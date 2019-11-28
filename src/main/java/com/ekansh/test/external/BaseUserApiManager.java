package com.ekansh.test.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */

/**
 * Base Api Manager which implements methods of RestApiManager and set basic request headers too.
 */
public class BaseUserApiManager extends RestApiManager {

    @Value("${external.users.api.base.url}")
    private String externalUsersApiBaseUrl;

    @Override
    protected String getBaseUrl() {
        return externalUsersApiBaseUrl;
    }

    public HashMap<String, String> getRequestHeaders() {
        HashMap<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put(HttpHeaders.CONTENT_TYPE, "application/json");
        return requestHeaders;
    }
}
