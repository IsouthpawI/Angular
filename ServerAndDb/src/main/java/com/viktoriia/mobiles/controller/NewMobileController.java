package com.viktoriia.mobiles.controller;

import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.entity.PersonNumber;
import com.viktoriia.mobiles.entity.User;
import com.viktoriia.mobiles.exception.BadRequestException;
import com.viktoriia.mobiles.exception.MobileNotFoundException;
import com.viktoriia.mobiles.exception.NoContentInDataBase;
import com.viktoriia.mobiles.exception.ServerError;
import com.viktoriia.mobiles.service.IMobilesService;
import com.viktoriia.mobiles.service.IMobilesSpecialSearchService;
import com.viktoriia.mobiles.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.plaf.basic.BasicTreeUI;
import java.util.List;


//TODO: ne obrabatyvaetsa internal server error 500daze v custom...
//500 - SERVER ERROR is possible with all the above HTTP methods. In the case of a 500, include the contact details of the help desk in the response.
@Controller
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class NewMobileController {

    @Autowired
    private IMobilesService mobileService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IMobilesSpecialSearchService mobileSpecialService;

    //=================GET==============================
    @GetMapping("/mobiles/get/{phonenumberm}")
    public ResponseEntity<Mobile> getMobileById(@PathVariable("phonenumberm") long id) {
        //Mobile mobile = mobileService.getMobileById(id);
        //if (mobile == null) throw new MobileNotFoundException("id: " + id);
        //return new ResponseEntity<Mobile>(mobile, HttpStatus.OK);
        return new ResponseEntity<Mobile>(mobileService.getMobileById(id), HttpStatus.OK);

    }

    @GetMapping("/mobiles/get/person/{phonenumberm}")
    public ResponseEntity<PersonNumber> getPersonById(@PathVariable("phonenumberm") long id) {
        //PersonNumber person = mobileSpecialService.getPersonNumberByMobile(mobileService.getMobileById(id));
       // if (person == null) throw new MobileNotFoundException("id: " + id);
        //return new ResponseEntity<PersonNumber>(person, HttpStatus.OK);
        return new ResponseEntity<PersonNumber>(mobileSpecialService.getPersonNumberByMobile(mobileService.getMobileById(id)), HttpStatus.OK);

    }

    @GetMapping("/mobiles")
    public ResponseEntity<List<Mobile>> getAllMobiles() {
        //List<Mobile> list = mobileService.getAllMobiles();
        //if (list.isEmpty()) throw new MobileNotFoundException("There is no mobiles");
        //return new ResponseEntity<List<Mobile>>(list, HttpStatus.OK);
        return new ResponseEntity<List<Mobile>>(mobileService.getAllMobiles(), HttpStatus.OK);
    }

    //TODO: ??????
    @GetMapping("/mobiles/{numberOfShowedMobiles}/{firstIndex}/{finishIndex}")
    public ResponseEntity<List<Mobile>> getMobilesList(@PathVariable("numberOfShowedMobiles") long numberOfShowedMobiles, @PathVariable("firstIndex") long firstIndex, @PathVariable("finishIndex") long finishIndex) {
//        List<Mobile> list = mobileService.getMobilesList( numberOfShowedMobiles, firstIndex, finishIndex);
//        if (list.isEmpty()) throw new MobileNotFoundException("There is no mobiles");
        //return new ResponseEntity<List<Mobile>>(list, HttpStatus.OK);
        return new ResponseEntity<List<Mobile>>(mobileService.getMobilesList( numberOfShowedMobiles, firstIndex, finishIndex), HttpStatus.OK);
    }

    @GetMapping("/mobiles/list")
    public ResponseEntity<List<Mobile>> getMobilesList2(@RequestParam(name = "numberOfShowedMobiles", required = true) long numberOfShowedMobiles, @RequestParam(name = "firstIndex", required = true) long firstIndex, @RequestParam(name = "finishIndex", required = true) long finishIndex) {
//        List<Mobile> list = mobileService.getMobilesList( numberOfShowedMobiles, firstIndex, finishIndex);
//        if (list.isEmpty()) throw new MobileNotFoundException("There is no mobiles");
        //return new ResponseEntity<List<Mobile>>(list, HttpStatus.OK);
        return new ResponseEntity<List<Mobile>>(mobileService.getMobilesList( numberOfShowedMobiles, firstIndex, finishIndex), HttpStatus.OK);
    }




    @GetMapping("/mobiles/count")
    public ResponseEntity<Integer> getCountOfMobiles() {
//        System.out.println("COUNT");
//        int count = mobileService.getCountOfMobile();
//        if (count == 0) throw new MobileNotFoundException("The count of mobiles is 0 (there is no mobiles)");
       // return new ResponseEntity<Integer>(count, HttpStatus.OK);
        return new ResponseEntity<Integer>(mobileService.getCountOfMobile(), HttpStatus.OK);
    }


    //===================CREATE=================================
    @PostMapping("/mobiles/create")
    public ResponseEntity<Void> createArticle(@RequestBody Mobile mobile, UriComponentsBuilder builder) {


//        boolean flag = mobileService.isExistMobile(mobile);
//        if (flag == false) throw new BadRequestException("Bad request for making Mobile, that mobile is already exist");
//        else mobileService.createMobile(mobile);
//

        mobileService.createMobile(mobile);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/mobiles/create?id={id}").buildAndExpand(mobile.getPhoneNumberM()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @PostMapping("/user/createUser")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {


//        boolean flag = mobileService.isExistMobile(mobile);
//        if (flag == false) throw new BadRequestException("Bad request for making Mobile, that mobile is already exist");
//        else mobileService.createMobile(mobile);
//
        System.out.println("CRESTE USER" + user);
        userService.createUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/createUser").buildAndExpand(user.getUsername()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }



    @PostMapping("/mobiles/create/person/{phonenumberm}")
    public ResponseEntity<Void> createPerson(@RequestBody PersonNumber personNumber,@PathVariable("phonenumberm") long phoneNumberM, UriComponentsBuilder builder) {

        mobileSpecialService.createPersonNumber(personNumber, mobileService.getMobileById(phoneNumberM));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/mobiles/create/person").buildAndExpand(personNumber.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    //===============UPDATE===============================
    @PutMapping("/mobiles/{id}")
    public ResponseEntity<Mobile> updateArticle(@PathVariable("id") long phoneNumberM, @RequestBody Mobile mobile) {
//        if (mobileService.getMobileById(phoneNumberM) == null) throw new MobileNotFoundException("There is no mobile with that id: " + phoneNumberM);
//       // if (mobileSpecialService.getPersonNumberByMobile(mobile) == null) throw new MobileNotFoundException("There is no personMoible");

        mobileSpecialService.updatePersonNumber(mobile);
        mobileService.updateMobile(phoneNumberM, mobile);
        return new ResponseEntity<Mobile>(mobile, HttpStatus.OK);
    }
//===============DELETE====================
    @DeleteMapping("/mobiles/{phonenumberm}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("phonenumberm") long phoneNumberM) {
       // if (mobileService.getMobileById(phoneNumberM) == null) throw new MobileNotFoundException("There is no mobile with that id: " + phoneNumberM);

       // if (mobileSpecialService.getPersonNumberByMobile(mobileService.getMobileById(phoneNumberM)) == null) throw new MobileNotFoundException("There is no personMoible");

//        if(!mobileSpecialService.deletePersonNumber(mobileService.getMobileById(phoneNumberM))){
//                throw new ServerError("internal Server error");
//        }

        mobileSpecialService.deletePersonNumber(mobileService.getMobileById(phoneNumberM));
        //else {
            //System.out.println("LALALA DELETE MOBILE");
        mobileService.deleteMobile(phoneNumberM);
       // }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

        @DeleteMapping("/mobiles/delete")
    public ResponseEntity<Void> deleteAllMobiles() {
//        if(!mobileSpecialService.deleteAllPersonNumber()){
//            throw new ServerError("internal Server error");
//        }
        mobileSpecialService.deleteAllPersonNumber();
        mobileService.deleteAllMobile();
//        boolean flag = mobileService.deleteAllMobile();
//        if (!flag) throw new MobileNotFoundException("There is no mobiles in content");
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


//=============SEARCH====================
//    @GetMapping(value = "mobiles/{firstName}/{lastName}/{address}/{phoneNumberM}/{phoneNumberH}")
//    public ResponseEntity<List<Mobile>> findByParameters(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String address, @PathVariable long phoneNumberM, @PathVariable long phoneNumberH) {
////        List<Mobile> list = mobileService.findByParameters(firstName, lastName, address, phoneNumberM, phoneNumberH);
////        if (list.isEmpty()) throw new BadRequestException("There is no content by your request");
//        //return new ResponseEntity<List<Mobile>>(list, HttpStatus.OK);
//        return new ResponseEntity<List<Mobile>>(mobileService.findByParameters(firstName, lastName, address, phoneNumberM, phoneNumberH), HttpStatus.OK);
//    }

    @GetMapping(value = "mobiles/searchMobile")
    public ResponseEntity<List<Mobile>> findByParameters2(@RequestParam(name = "firstname", required = false) String firstName, @RequestParam(name ="lastname", required = false)String lastName, @RequestParam(name ="address", required = false) String address, @RequestParam(name ="phonenumberm", required = false) String phoneNumberM, @RequestParam(name = "phonenumberh", required = false) String phoneNumberH, Model model) {
//        List<Mobile> list = mobileService.findByParameters(firstName, lastName, address, phoneNumberM, phoneNumberH);
//        if (list.isEmpty()) throw new BadRequestException("There is no content by your request");
        //return new ResponseEntity<List<Mobile>>(list, HttpStatus.OK);

//        if (lastName.equals("")){
//            lastName = null;
//        }
//        if (firstName.equals("")){
//            firstName = null;
//        }
//        if (address.equals("")){
//            address = null;
//        }
//        if (phoneNumberM.equals("0")){
//            phoneNumberM = null;
//        }
//        if (phoneNumberH.equals("0")){
//            phoneNumberH = null;
//        }


        //System.out.println("FIRSTNAME: " + firstName);

        model.addAttribute("firstname", firstName);
        model.addAttribute("lastname", lastName);
        model.addAttribute("address", address);
        model.addAttribute("phonenumberm", phoneNumberM);
        model.addAttribute("phonenumberh", phoneNumberH);


        return new ResponseEntity<List<Mobile>>(mobileService.findByParameters2(firstName, lastName, address, phoneNumberM, phoneNumberH), HttpStatus.OK);
    }


//    @GetMapping(value = "mobiles/searchNew/{firstName}/{lastName}")
//    public ResponseEntity<List<PersonNumber>> findNumbersByFirstNameOrLastName(@PathVariable String firstName, @PathVariable String lastName) {
////        System.out.println("KAKA");
////        List<PersonNumber> list = mobileSpecialService.findNumbersByFirstNameOrLastName(firstName, lastName);
////        if (list.isEmpty()) throw new BadRequestException("There is no content by your request");
//        //return new ResponseEntity<List<PersonNumber>>(list, HttpStatus.OK);
//        return new ResponseEntity<List<PersonNumber>>(mobileSpecialService.findNumbersByFirstNameOrLastName(firstName, lastName), HttpStatus.OK);
//    }

    //PEREDELAT SEARCHDAO
    @GetMapping(value = "mobiles/searchNew/new")
    public ResponseEntity<List<PersonNumber>> findNumbersByFirstNameOrLastName1(@RequestParam(name = "firstname", required = false) String firstName, @RequestParam(name = "lastname", required = false) String lastName, Model model) {
        System.out.println("LASTNAME: " + lastName);



        model.addAttribute("firstname", firstName);
        model.addAttribute("lastname", lastName);

        //        System.out.println("KAKA");
//        List<PersonNumber> list = mobileSpecialService.findNumbersByFirstNameOrLastName(firstName, lastName);
//        if (list.isEmpty()) throw new BadRequestException("There is no content by your request");
        //return new ResponseEntity<List<PersonNumber>>(list, HttpStatus.OK);
        //return new ResponseEntity<List<PersonNumber>>(mobileSpecialService.findNumbersByFirstNameOrLastName(firstName, lastName), HttpStatus.OK);
        return new ResponseEntity<List<PersonNumber>>(mobileSpecialService.findNumbersByFirstNameOrLastName2(firstName, lastName), HttpStatus.OK);
    }


}
