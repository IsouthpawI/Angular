package com.viktoriia.mobiles.service;

import com.viktoriia.mobiles.dao.IUserDAO;
import com.viktoriia.mobiles.entity.User;
import com.viktoriia.mobiles.exception.BadRequestException;
import com.viktoriia.mobiles.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDAO userDAO;


    @Override
    public User getUserById(String username) {
        // Mobile mobile = mobileService.getMobileById(id);
        User user = userDAO.getUserById(username);
        if (user == null) throw new UserExistException("User has already exist with that username: " + username);
        return user;
    }

    @Override
    public void createUser(User user) {
        boolean flag = isExistUser(user);
        // boolean flag1 = mobileSpecialService.createPersonNumber(personNumber);
        if (flag) throw new BadRequestException("Bad request for making User, that user has already exist");
        else userDAO.createUser(user);

    }

    @Override
    public synchronized boolean isExistUser(User user) {
        System.out.println("GET USERNAME" + user.getUsername());
        if (userDAO.userExists(user.getUsername())) {
            return false;
        } else {
            return true;
        }
    }

//
//    @Override
//    public boolean userExists(String username) {
//        return false;
//    }

    @Override
    public boolean checkedUser(User user) {
        if (userDAO.checkUser(user.getUsername(), user.getPassword())) {
            return false;
        } else {
            return true;
        }
    }
}
