package com.viktoriia.mobiles.dao;

import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.entity.User;

import java.util.Date;
import java.util.List;

public interface IUserDAO {
    User getUserById(String username);
    void createUser(User user);
//    void updateMobile(Mobile mobile);
//    void updateMobile(long phoneNumberM, Mobile mobile);
//    void deleteMobile(long phoneNumberM);
    boolean userExists(String username);
    boolean checkUser(String username, String password);

//    int getCountOfMobiles();

    //?????
//    List<User> getUsersList(long numberOfShowedMobiles, long firstIndex, long finishIndex);
    //List<Mobile> getMobilesList2(long numberOfShowedMobiles, long firstIndex, long finishIndex);

//    boolean deleteAllMobile();

//    List<Mobile> findByParameters(String firstName, String lastName, String address, long phoneNumberM, long phoneNumberH);
//    List<Mobile> findByParameters2(String firstName, String lastName, String address, String phoneNumberM, String phoneNumberH);

}
