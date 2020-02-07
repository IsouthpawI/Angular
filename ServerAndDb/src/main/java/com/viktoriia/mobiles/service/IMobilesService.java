package com.viktoriia.mobiles.service;

import com.viktoriia.mobiles.entity.Mobile;

import java.util.List;

public interface IMobilesService {
    List<Mobile> getAllMobiles();
    Mobile getMobileById(long phoneNumberM);
    void createMobile(Mobile mobile);
    boolean isExistMobile(Mobile mobile);
    void updateMobile(Mobile mobile);
    void updateMobile(long phoneNumberM, Mobile mobile);
    void deleteMobile(long phoneNumberM);

    int getCountOfMobile();

    List<Mobile> getMobilesList(long numberOfShowedMobiles, long firstIndex, long finishIndex);
    //List<Mobile> getMobilesList2(long numberOfShowedMobiles, long firstIndex, long finishIndex);

    void deleteAllMobile();

    List<Mobile> findByParameters(String firstName, String lastName, String address, long phoneNumberM, long phoneNumberH);
    List<Mobile> findByParameters2(String firstName, String lastName, String address, String phoneNumberM, String phoneNumberH);
}
