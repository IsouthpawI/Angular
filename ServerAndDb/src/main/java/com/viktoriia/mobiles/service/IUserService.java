package com.viktoriia.mobiles.service;

import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.entity.User;

import java.util.List;

public interface IUserService {
    User getUserById(String username);
    void createUser(User user);
    boolean isExistUser(User user);
//    boolean userExists(String username);
//    boolean checkUser(String username, String password);
    boolean checkedUser(User user);
}
