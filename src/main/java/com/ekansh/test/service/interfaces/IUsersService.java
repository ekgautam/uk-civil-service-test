package com.ekansh.test.service.interfaces;

import com.ekansh.test.pojo.Users;

import java.util.List;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
public interface IUsersService {


    List<Users> getUsers(String city, Double milesWithin);
}
