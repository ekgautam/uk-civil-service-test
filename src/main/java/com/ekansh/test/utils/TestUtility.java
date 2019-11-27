package com.ekansh.test.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@Slf4j
public class TestUtility {

    private TestUtility() {
    }

    private static final int ERROR_ID_SIZE = 6;

    public static String getUniqueErrorId() {
        return RandomStringUtils.randomAlphanumeric(ERROR_ID_SIZE);
    }

    public static String getSecureRandomToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[10];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token;

    }


}
