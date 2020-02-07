package com.viktoriia.mobiles.service;

import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.entity.PersonNumber;

import java.util.List;

public interface IMobilesSpecialSearchService {
    List<PersonNumber> findNumbersByFirstNameOrLastName(String firstName, String lastName);
    List<PersonNumber> findNumbersByFirstNameOrLastName2(String firstName, String lastName);
    void createPersonNumber(PersonNumber personNumber, Mobile mobile);

    void deletePersonNumber(Mobile mobileById);

    PersonNumber getPersonNumberByMobile(Mobile mobileById);

    void deleteAllPersonNumber();

    void updatePersonNumber(Mobile mobile);
}
