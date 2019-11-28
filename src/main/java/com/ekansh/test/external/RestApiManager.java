package com.ekansh.test.external;

import com.ekansh.test.exception.InternalServerException;
import com.ekansh.test.utils.HeaderBuilder;
import com.ekansh.test.utils.TestUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */

/**
 * Rest Api Manager is the generic api manager which contains the boiler plate code used to call external service.
 * It handles exception too and returns a generic message along with tag/token. This token can be used to track requests
 * logging.
 */
@Component
@Slf4j
public abstract class RestApiManager {

    private final String REST_RESP_TOKEN = "RestAPI response received with token: {} and body: {}";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HeaderBuilder headerBuilder;

    abstract protected String getBaseUrl();


    protected <T> T get(String url, String query, HashMap<String, String> requestHeaders, Type responseClassType, String tag) {

        ResponseEntity<T> responseEntity;

        String fullUrl = getFullUrl(getBaseUrl(), url, query);
        log.info(fullUrl);
        HttpEntity<?> httpEntity;
        if (requestHeaders != null) {
            httpEntity = new HttpEntity<>(headerBuilder.buildHeaders(requestHeaders));
        } else {
            httpEntity = null;
        }
        try {
            responseEntity =
                    restTemplate.exchange(
                            fullUrl,
                            HttpMethod.GET,
                            httpEntity,
                            new ParameterizedTypeReference<T>() {
                                @Override
                                public Type getType() {
                                    return responseClassType;
                                }
                            });

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                log.info(REST_RESP_TOKEN, responseEntity);
                return responseEntity.getBody();
            } else {
                logError(responseEntity, TestUtility.getSecureRandomToken());
            }
        } catch (ResourceAccessException ex) {
            throw new InternalServerException("I/O error on get request" + ex.toString());
        } catch (RestClientException ex) {
            onExceptionHandle(ex, tag);
        }
        return null;
    }

    private String getFullUrl(String baseUrl, String url, String query) {
        StringBuilder fullUrl = new StringBuilder();
        fullUrl.append(baseUrl);
        if (url != null) {
            fullUrl.append(url);
        }
        if (query != null && query.startsWith("?")) {
            query = query.substring(1);
        }
        query = StringUtils.trimToNull(query);
        if (query != null) {
            fullUrl.append("?");
            fullUrl.append(query);
        }
        return fullUrl.toString();
    }

    protected Object onExceptionHandle(RestClientException ex, String token) {

        log.error("RestClientException in RestApiManager with token: {}", token);
        log.error("uuid: {}, Server error: Message {}", token, ex.toString());

        throw new InternalServerException("Something went wrong! \nPlease Try again. with uuid: " + token);
    }


    private <T> void logError(ResponseEntity<T> responseEntity, String token) {
        log.error("uuid is: {},  response received is not valid http code: {} and entity is : {}",
                token, responseEntity.getStatusCode(), responseEntity.toString());
        throw new InternalServerException("Something went wrong! with id: " + token);
    }


}

