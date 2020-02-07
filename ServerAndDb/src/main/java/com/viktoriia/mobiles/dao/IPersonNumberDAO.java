package com.viktoriia.mobiles.dao;

import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.entity.PersonNumber;

import java.util.Date;
import java.util.List;

public interface IPersonNumberDAO {
    void createPersonNumber(PersonNumber personNumber, Mobile mobile );
    boolean personNumberExists(long idPhoneNumber);
    List<PersonNumber> findNumbersByFirstNameOrLastName(String firstName, String lastName);
    List<PersonNumber> findNumbersByFirstNameOrLastName2(String firstName, String lastName);

    boolean deletePersonNumber(Mobile mobileById);

    PersonNumber getPersonNumberByMobile(Mobile mobileById);

    boolean deleteAllPersons();

    void updatePersonNumber(Mobile mobile);
}
