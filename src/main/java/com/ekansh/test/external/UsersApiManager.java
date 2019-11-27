package com.ekansh.test.external;

import com.ekansh.test.pojo.Users;
import com.ekansh.test.utils.TestUtility;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@Component
@Slf4j
public class UsersApiManager extends BaseUserApiManager {

    private static final String USERS_INDEX = "/users";

    private static final String CITY_INDEX = "/city/";

    public List<Users> getUsersByCity(String city) {

        try {
            return super.get(CITY_INDEX + city + USERS_INDEX, null, getRequestHeaders(),
                    new TypeToken<ArrayList<Users>>() {
                    }.getType(), TestUtility.getSecureRandomToken());
        } catch (Exception e) {
            // here use case is not to break the api but to result users so may be exception handling can be done or not.
            log.error("Couldn't get data from external api so returning empty users");
        }

        return new ArrayList<>();

    }

    public List<Users> getAllUsers() {
        try {
            return super.get(USERS_INDEX, null, getRequestHeaders(),
                    new TypeToken<ArrayList<Users>>() {
                    }.getType(), TestUtility.getSecureRandomToken());
        } catch (Exception e) {
            // here use case is not to break the api but to result users so may be exception handling can be done or not.
            log.error("Couldn't get data from external api so returning empty users");
        }

        return new ArrayList<>();
    }


}
