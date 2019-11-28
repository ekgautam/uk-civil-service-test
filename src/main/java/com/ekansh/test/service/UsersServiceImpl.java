package com.ekansh.test.service;

import com.ekansh.test.exception.UnprocessableEntityException;
import com.ekansh.test.external.UsersApiManager;
import com.ekansh.test.pojo.Users;
import com.ekansh.test.requestvalidators.UsersRequestValidation;
import com.ekansh.test.response.ValidationResponse;
import com.ekansh.test.service.interfaces.IUsersService;
import com.ekansh.test.utils.MathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private UsersRequestValidation usersRequestValidation;

    @Autowired
    private UsersApiManager usersApiManager;

//    As London's lat long were not provided in the question I have taken them up from Internet.
    private static final Double LONDON_LAT = 51.509865;
    private static final Double LONDON_LONG = -0.118092;
    private static final String LONDON = "London";

    /**
     * This method validates the request and return filtered {@code List<Users>} based on {@code city} and {@code milesWithin}.
     * If {@code city} and {@code milesWithin} both are null it is considered as invalid request else whichever is not null
     * will be considered for filtering.
     */

    @Override
    @SuppressWarnings("unchecked")
    public List<Users> getUsers(String city, Double milesWithin) {

        List<Users> filteredUsers = new ArrayList<>();

        ValidationResponse validationResponse = usersRequestValidation.validateRequest(city, milesWithin);

        if (!validationResponse.getIsValid()) {
            throw new UnprocessableEntityException("Invalid request: " + validationResponse.getErrors());
        }

        List<Users> cityUsers = new ArrayList<>();
        List<Users> allUsers = usersApiManager.getAllUsers();

//        if city has been provided as empty string London will be picked up
        if (StringUtils.isNotEmpty(city)) {
            cityUsers = usersApiManager.getUsersByCity(city);
        } else {
            usersApiManager.getUsersByCity(LONDON);
        }

        filteredUsers.addAll(cityUsers);

        getUsersFromMilesWithin(milesWithin, new ArrayList<>(CollectionUtils.subtract(allUsers, cityUsers)), filteredUsers);

        return filteredUsers;
    }

    private void getUsersFromMilesWithin(Double milesWithin, List<Users> remainingUsers, List<Users> filteredUsers) {
        if (milesWithin != null) {

            remainingUsers.forEach(user -> {
                Double dist = MathUtil.getMiles(MathUtil.distance(LONDON_LAT, user.getLatitude(), LONDON_LONG, user.getLongitude()));
                if (dist <= milesWithin) {
                    filteredUsers.add(user);
                }

            });
        }
    }
}
