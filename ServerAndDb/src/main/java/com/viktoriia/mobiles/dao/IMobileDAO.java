package com.viktoriia.mobiles.dao;

import com.viktoriia.mobiles.entity.Mobile;

import java.util.Date;
import java.util.List;

public interface IMobileDAO {
    List<Mobile> getAllMobiles();
    Mobile getMobileById(long phoneNumberM);
    void createMobile(Mobile mobile);
    void updateMobile(Mobile mobile);
    void updateMobile(long phoneNumberM, Mobile mobile);
    void deleteMobile(long phoneNumberM);
    boolean mobileExists(String firstName, String lastName, String address, long phoneNumberM, long phoneNumberH, Date added);

    int getCountOfMobiles();

    List<Mobile> getMobilesList(long numberOfShowedMobiles, long firstIndex, long finishIndex);
    //List<Mobile> getMobilesList2(long numberOfShowedMobiles, long firstIndex, long finishIndex);

    boolean deleteAllMobile();

    List<Mobile> findByParameters(String firstName, String lastName, String address, long phoneNumberM, long phoneNumberH);
    List<Mobile> findByParameters2(String firstName, String lastName, String address, String phoneNumberM, String phoneNumberH);

    //List<Mobile> findNumbersByFirstNameOrLastName(String firstName, String lastName);
}
