//package com.viktoriia.mobiles.repositories;
//
//import com.viktoriia.mobiles.entity.Mobile;
//
//import java.util.List;
//
//import org.springframework.data.repository.CrudRepository;
//
//public interface MobileRepository extends CrudRepository<Mobile, Long> {
//    List<Mobile> findByFirstNameAndLastNameAndAddressAndPhoneNumberMAndPhoneNumberH(String firstName, String lastName, String address, long phoneNumberM, long phoneNumberH);
//
//    List<Mobile> findByParameters(String firstName);
//
//    List<Mobile> findByLastName(String lastName);
//
//    List<Mobile> findByAddress(String lastName);
//
//    List<Mobile> findByPhoneNumberM(long phoneNumberM);
//
//    List<Mobile> findByPhoneNumberH(long phoneNumberH);
//
//    List<Mobile> findByFirstNameAndLastName(String firstName, String lastName);
//
//    List<Mobile> findByFirstNameAndAddress(String firstName, String address);
//
//    List<Mobile> findByFirstNameAndPhoneNumberM(String firstName, long phoneNumberM);
//
//    List<Mobile> findByFirstNameAndPhoneNumberH(String firstName, long phoneNumberH);
//
//    List<Mobile> findByLastNameAndAddress(String lastName, String address);
//
//    List<Mobile> findByLastNameAndPhoneNumberM(String lastName, long phoneNumberM);
//
//    List<Mobile> findByLastNameAndPhoneNumberH(String lastName, long phoneNumberH);
//
//    List<Mobile> findByAddressAndPhoneNumberM(String address, long phoneNumberM);
//
//    List<Mobile> findByAddressAndPhoneNumberH(String address, long phoneNumberH);
//
//    List<Mobile> findByPhoneNumberMAndPhoneNumberH(long phoneNumberM, long phoneNumberH);
//
//    List<Mobile> findByFirstNameAndLastNameAndAddress(String firstName, String lastName, String address);
//
//    List<Mobile> findByFirstNameAndLastNameAndPhoneNumberM(String firstName, String lastName, long phoneNumberM);
//
//    List<Mobile> findByFirstNameAndLastNameAndPhoneNumberH(String firstName, String lastName, long phoneNumberH);
//
//    List<Mobile> findByFirstNameAndAddressAndPhoneNumberM(String firstName, String address, long phoneNumberM);
//
//    List<Mobile> findByFirstNameAndAddressAndPhoneNumberH(String firstName, String address, long phoneNumberH);
//
//    List<Mobile> findByFirstNameAndPhoneNumberMAndPhoneNumberH(String firstName, long phoneNumberM, long phoneNumberH);
//
//    List<Mobile> findByLastNameAndPhoneNumberMAndPhoneNumberH(String lastName, long phoneNumberM, long phoneNumberH);
//
//    List<Mobile> findByAddressAndPhoneNumberMAndPhoneNumberH(String address, long phoneNumberM, long phoneNumberH);
//
//    List<Mobile> findByLastNameAndAddressAndPhoneNumberM(String lastName, String address, long phoneNumberM);
//
//    List<Mobile> findByLastNameAndAddressAndPhoneNumberH(String lastName, String address, long phoneNumberH);
//
//    List<Mobile> findByFirstNameAndLastNameAndAddressAndPhoneNumberM(String firstName, String lastName, String address, long phoneNumberM);
//
//    List<Mobile> findByFirstNameAndLastNameAndAddressAndPhoneNumberH(String firstName, String lastName, String address, long phoneNumberH);
//
//    List<Mobile> findByFirstNameAndLastNameAndPhoneNumberMAndPhoneNumberH(String firstName, String lastName, long phoneNumberM, long phoneNumberH);
//
//    List<Mobile> findByFirstNameAndAddressAndPhoneNumberMAndPhoneNumberH(String firstName, String address, long phoneNumberM, long phoneNumberH);
//
//    List<Mobile> findByLastNameAndAddressAndPhoneNumberMAndPhoneNumberH(String lastName, String address, long phoneNumberM, long phoneNumberH);
//
//}
