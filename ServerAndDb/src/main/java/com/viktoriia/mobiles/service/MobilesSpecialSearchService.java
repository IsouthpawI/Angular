package com.viktoriia.mobiles.service;

import com.viktoriia.mobiles.dao.IMobileDAO;
import com.viktoriia.mobiles.dao.IPersonNumberDAO;
import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.entity.PersonNumber;
import com.viktoriia.mobiles.exception.BadRequestException;
import com.viktoriia.mobiles.exception.MobileNotFoundException;
import com.viktoriia.mobiles.exception.ServerError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobilesSpecialSearchService implements IMobilesSpecialSearchService{
    @Autowired
    private IPersonNumberDAO personNumberDAO;




    @Override
    public List<PersonNumber> findNumbersByFirstNameOrLastName(String firstName, String lastName) {

        List<PersonNumber> list = personNumberDAO.findNumbersByFirstNameOrLastName(firstName, lastName);
        if (list.isEmpty()) throw new BadRequestException("There is no content by your request");

//        return personNumberDAO.findNumbersByFirstNameOrLastName(firstName, lastName);
        return list;
    }

    @Override
    public List<PersonNumber> findNumbersByFirstNameOrLastName2(String firstName, String lastName) {

        List<PersonNumber> list = personNumberDAO.findNumbersByFirstNameOrLastName2(firstName, lastName);
        if (list.isEmpty()) throw new BadRequestException("There is no content by your request");

//        return personNumberDAO.findNumbersByFirstNameOrLastName(firstName, lastName);
        return list;
    }

    @Override
    public synchronized void createPersonNumber(PersonNumber personNumber, Mobile mobile) {
       // boolean flag1 = mobileSpecialService.createPersonNumber(personNumber, mobileService.getMobileById(phoneNumberM));
       // if (flag1 == false) throw new BadRequestException("Bad request for making personNumber");

        if (mobile == null){
            throw new MobileNotFoundException("There is no mobile");
        }
        System.out.println(mobile.toString());
        if (personNumberDAO.personNumberExists(mobile.getPhoneNumberM())) {
            throw new BadRequestException("Bad request for making personNumber");
        } else {
            personNumberDAO.createPersonNumber(personNumber, mobile);
           // return true;
        }
    }

    @Override
    public void deletePersonNumber(Mobile mobileById) {
        if (mobileById == null) throw new MobileNotFoundException("There is no mobile");
        if (getPersonNumberByMobile(mobileById) == null) throw new MobileNotFoundException("There is no personMobile");
        if(!personNumberDAO.deletePersonNumber(mobileById)){
            throw new ServerError("internal Server error");
        }

       // return personNumberDAO.deletePersonNumber(mobileById);
    }

    @Override
    public PersonNumber getPersonNumberByMobile(Mobile mobileById) {
        if (mobileById == null){
            throw new MobileNotFoundException("There is no mobile.");
        }
        PersonNumber person = personNumberDAO.getPersonNumberByMobile(mobileById);
        if (person == null) throw new MobileNotFoundException("There is no person with mobile number: " + mobileById.getPhoneNumberM());
        return person;
    }

    @Override
    public void deleteAllPersonNumber() {
        if(!personNumberDAO.deleteAllPersons()){
            throw new ServerError("internal Server error");
        }


        //return personNumberDAO.deleteAllPersons();
    }

    @Override
    public void updatePersonNumber(Mobile mobile) {
        if (mobile == null){
            throw new MobileNotFoundException("There is no mobile");
        }
        //if (getPersonNumberByMobile(mobile) == null) throw new MobileNotFoundException("There is no person with that mobile number: " + mobile.getPhoneNumberM());
        personNumberDAO.updatePersonNumber(mobile);
    }


}
