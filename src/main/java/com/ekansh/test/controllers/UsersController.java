package com.ekansh.test.controllers;

import com.ekansh.test.pojo.Users;
import com.ekansh.test.response.BaseObjectResponse;
import com.ekansh.test.response.ServiceResponse;
import com.ekansh.test.service.interfaces.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UsersController {

    @Autowired
    IUsersService userService;

    /**
     * This controller returns the user within given miles as query param {@code miles_within}.
     * Return type is {@code List<Users>} and users POJO class is same as the api response.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ServiceResponse<BaseObjectResponse<List<Users>>> getNearbyUsers(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "miles_within", required = false) Double milesWithin) {

        return new ServiceResponse<>(new BaseObjectResponse<>(userService.getUsers(city, milesWithin)));
    }
}
