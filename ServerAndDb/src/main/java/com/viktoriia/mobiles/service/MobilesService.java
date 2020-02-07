package com.viktoriia.mobiles.service;

import com.viktoriia.mobiles.dao.IMobileDAO;
import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.exception.BadRequestException;
import com.viktoriia.mobiles.exception.MobileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
//@Component
public class MobilesService implements IMobilesService{
    @Autowired
    private IMobileDAO mobileDAO;


    @Override
    public Mobile getMobileById(long phoneNumberM) {
       // Mobile mobile = mobileService.getMobileById(id);
        Mobile mobile = mobileDAO.getMobileById(phoneNumberM);
        if (mobile == null) throw new MobileNotFoundException("There is no mobile with that id: " + phoneNumberM);
        return mobile;
    }

    @Override
    public List<Mobile> getAllMobiles() {

        List<Mobile> list = mobileDAO.getAllMobiles();
        if (list.isEmpty()) throw new MobileNotFoundException("There is no mobiles");
        //return mobileDAO.getAllMobiles();
        return list;
    }

    @Override
    public synchronized boolean isExistMobile(Mobile mobile) {
        if (mobileDAO.mobileExists(mobile.getFirstName(), mobile.getLastName(), mobile.getAddress(), mobile.getPhoneNumberM(), mobile.getPhoneNumberH(), mobile.getAdded())) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public synchronized void createMobile(Mobile mobile) {
        boolean flag = isExistMobile(mobile);
        // boolean flag1 = mobileSpecialService.createPersonNumber(personNumber);
        if (!flag) throw new BadRequestException("Bad request for making Mobile, that mobile is already exist");
        else mobileDAO.createMobile(mobile);
       // mobileDAO.createMobile(mobile);
    }

    @Override
    public void updateMobile(Mobile mobile) {
        mobileDAO.updateMobile(mobile);
    }

    @Override
    public void updateMobile(long phoneNumberM, Mobile mobile) {
        if (getMobileById(phoneNumberM) == null) throw new MobileNotFoundException("There is no mobile with that id: " + phoneNumberM);
        mobileDAO.updateMobile(phoneNumberM, mobile);
    }

    @Override
    public void deleteMobile(long phoneNumberM) {
        if (getMobileById(phoneNumberM) == null) throw new MobileNotFoundException("There is no mobile with that id: " + phoneNumberM);
        mobileDAO.deleteMobile(phoneNumberM);
    }


    public int getCountOfMobile(){
        System.out.println("COUNT");
        int count = mobileDAO.getCountOfMobiles();
        if (count == 0) throw new MobileNotFoundException("The count of mobiles is 0 (there is no mobiles)");
        //return mobileDAO.getCountOfMobiles();
        return count;
    }

    public List<Mobile> getMobilesList(long numberOfShowedMobiles, long firstIndex, long finishIndex){
        List<Mobile> list = mobileDAO.getMobilesList( numberOfShowedMobiles, firstIndex, finishIndex);
        if (list.isEmpty()) throw new MobileNotFoundException("There is no mobiles");
        //return mobileDAO.getMobilesList(numberOfShowedMobiles, firstIndex, finishIndex);
        return list;
    }

//    public List<Mobile> getMobilesList2(long numberOfShowedMobiles, long firstIndex, long finishIndex){
//        List<Mobile> list = mobileDAO.getMobilesList2( numberOfShowedMobiles, firstIndex, finishIndex);
//        if (list.isEmpty()) throw new MobileNotFoundException("There is no mobiles");
//        //return mobileDAO.getMobilesList(numberOfShowedMobiles, firstIndex, finishIndex);
//        return list;
//    }

    @Override
    public void deleteAllMobile() {
        boolean flag = mobileDAO.deleteAllMobile();
        if (!flag) throw new MobileNotFoundException("There is no mobiles in content");

        //return mobileDAO.deleteAllMobile();
    }

    @Override
    public List<Mobile> findByParameters(String firstName, String lastName, String address, long phoneNumberM, long phoneNumberH) {
        List<Mobile> list = mobileDAO.findByParameters(firstName, lastName, address, phoneNumberM, phoneNumberH);
        if (list.isEmpty()) throw new BadRequestException("There is no content by your request");
        return list;
//        return mobileDAO.findByParameters(firstName, lastName, address, phoneNumberM, phoneNumberH);
    }

    @Override
    public List<Mobile> findByParameters2(String firstName, String lastName, String address, String phoneNumberM, String phoneNumberH) {
        List<Mobile> list = mobileDAO.findByParameters2(firstName, lastName, address, phoneNumberM, phoneNumberH);
        if (list.isEmpty()) throw new BadRequestException("There is no content by your request");
        return list;
//        return mobileDAO.findByParameters(firstName, lastName, address, phoneNumberM, phoneNumberH);
    }
}
