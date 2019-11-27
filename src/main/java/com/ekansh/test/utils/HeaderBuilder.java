package com.ekansh.test.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@Component
public class HeaderBuilder {

    public HttpHeaders buildHeaders(HashMap hashHeader) {
        if (hashHeader != null) {
            if (!hashHeader.containsKey("Content-Type")) {
                hashHeader.put("Content-Type", "application/json");
            }
            HttpHeaders headers = new HttpHeaders();
            Iterator iterator = hashHeader.entrySet().iterator();
            while (iterator.hasNext()) {
                HashMap.Entry pair = (Map.Entry) iterator.next();
                headers.add(pair.getKey().toString(), pair.getValue().toString());
            }

            return headers;
        }
        return null;
    }
}
