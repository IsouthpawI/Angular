//package com.viktoriia.mobiles.controller;
//
//
//import com.viktoriia.mobiles.entity.Mobile;
//import com.viktoriia.mobiles.repositories.MobileRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@CrossOrigin(origins = "http://localhost:4200")
//@RestController
//@RequestMapping("/api")
//public class MobileController {
//
//    @Autowired
//    MobileRepository repository;
//
//    @GetMapping("/mobiles")
//    public List<Mobile> getAllMobiles() {
//        System.out.println("GET ALL MOBILES");
//
//        List<Mobile> mobiles = new ArrayList<Mobile>();
//        repository.findAll().forEach(mobiles::add);
//
//        return mobiles;
//    }
//
//    @GetMapping("/mobiles/{numberOfShowedMobiles}/{firstIndex}/{finishIndex}")
//    public List<Mobile> getMobilesList(@PathVariable("numberOfShowedMobiles") long numberOfShowedMobiles, @PathVariable("firstIndex") long firstIndex, @PathVariable("finishIndex") long finishIndex) {
//        System.out.println("GET ALL MOBILES");
//
//        List<Mobile> mobiles = new ArrayList<Mobile>();
//        List<Mobile> mobiles1 = new ArrayList<Mobile>();
//
//        repository.findAll().forEach(mobiles::add);
//
//        if (finishIndex < mobiles.size()) {
//            for (; firstIndex <= finishIndex; firstIndex++) {
//                System.out.println("OTSCHET POSEL");
//                mobiles1.add(mobiles.get(Integer.parseInt(Long.toString(firstIndex))));
//            }
//        } else {
//            for (; firstIndex < mobiles.size(); firstIndex++) {
//                System.out.println("OTSCHET POSEL INDEX FINISH BOLSE CEM SIZE");
//                mobiles1.add(mobiles.get(Integer.parseInt(Long.toString(firstIndex))));
//            }
//        }
//
//
//        return mobiles1;
//    }
//
//    @GetMapping("/mobiles/count")
//    public int getCountOfMobiles() {
//        System.out.println("GET Count");
//
//        List<Mobile> mobiles = new ArrayList<Mobile>();
//        repository.findAll().forEach(mobiles::add);
//        System.out.println("SIZE IS " + mobiles.size());
//        return mobiles.size();
//    }
//
//    @GetMapping("/mobiles/get/{phonenumberm}")
//    public Mobile getMobile(@PathVariable("phonenumberm") long phoneNumberM) {
//        System.out.println("GET MOBILES");
//
//        Optional<Mobile> mobileData = repository.findById(phoneNumberM);
//        if (mobileData.isPresent()) {
//            return mobileData.get();
//        } else {
//            System.out.println("ERROR");
//            return null;
//        }
//    }
//
//    @PostMapping(value = "/mobiles/create")
//    public Mobile postMobile(@RequestBody Mobile mobile) {
//
//        Mobile mobile1 = repository.save(new Mobile(mobile.getFirstName(), mobile.getLastName(), mobile.getAddress(), mobile.getPhoneNumberM(), mobile.getPhoneNumberH(), mobile.getAdded()));
//        return mobile1;
//    }
//
//
//    @DeleteMapping("/mobiles/{phonenumberm}")
//    public ResponseEntity<String> deleteCustomer(@PathVariable("phonenumberm") long phoneNumberM) {
//        System.out.println("Delete Mobile with PHONENUMBERM = " + phoneNumberM + "...");
//
//        repository.deleteById(phoneNumberM);
//
//        return new ResponseEntity<>("Mobile has been deleted!", HttpStatus.OK);
//    }
//
//    @DeleteMapping("/mobiles/delete")
//    public ResponseEntity<String> deleteAllMobiles() {
//        System.out.println("Delete All Mobiles...");
//
//        repository.deleteAll();
//
//        return new ResponseEntity<>("All mobiles have been deleted!", HttpStatus.OK);
//    }
//
//    @GetMapping(value = "mobiles/firstName/{firstName}")
//    List<Mobile> findByParameters(@PathVariable String firstName) {
//        System.out.println("SEARCH");
//        List<Mobile> mobiles = repository.findByParameters(firstName);
//        return mobiles;
//    }
//
//    @GetMapping(value = "mobiles/{firstName}/{lastName}/{address}/{phoneNumberM}/{phoneNumberH}")
//    List<Mobile> findByParameters(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String address, @PathVariable long phoneNumberM, @PathVariable long phoneNumberH) {
//        System.out.println("SEARCH ALL");
//        List<Mobile> mobiles = repository.findByParameters(firstName);
//        if (lastName.equals("example") && address.equals("example") && phoneNumberM == 0 && phoneNumberH == 0) {
//            System.out.println("JUST NAME");
//            mobiles = repository.findByParameters(firstName);
//        } else if (firstName.equals("example") && address.equals("example") && phoneNumberM == 0 && phoneNumberH == 0) {
//            System.out.println("JUST Last NAME");
//            mobiles = repository.findByLastName(lastName);
//        } else if (firstName.equals("example") && lastName.equals("example") && phoneNumberM == 0 && phoneNumberH == 0) {
//            System.out.println("JUST Address");
//            mobiles = repository.findByAddress(lastName);
//        } else if (firstName.equals("example") && lastName.equals("example") && address.equals("example") && phoneNumberH == 0) {
//            System.out.println("JUST Mobile");
//            mobiles = repository.findByPhoneNumberM(phoneNumberM);
//        } else if (firstName.equals("example") && lastName.equals("example") && address.equals("example") && phoneNumberM == 0) {
//            System.out.println("JUST Home");
//            mobiles = repository.findByPhoneNumberH(phoneNumberH);
//        } else if (address.equals("example") && phoneNumberM == 0 && phoneNumberH == 0) {
//            System.out.println("First Last");
//            mobiles = repository.findByFirstNameAndLastName(firstName, lastName);
//        } else if (lastName.equals("example") && phoneNumberM == 0 && phoneNumberH == 0) {
//            System.out.println("FA");
//            mobiles = repository.findByFirstNameAndAddress(firstName, address);
//        } else if (lastName.equals("example") && address.equals("example") && phoneNumberH == 0) {
//            System.out.println("FM");
//            mobiles = repository.findByFirstNameAndPhoneNumberM(firstName, phoneNumberM);
//        } else if (lastName.equals("example") && address.equals("example") && phoneNumberM == 0) {
//            System.out.println("FH");
//            mobiles = repository.findByFirstNameAndPhoneNumberH(firstName, phoneNumberH);
//        } else if (firstName.equals("example") && phoneNumberM == 0 && phoneNumberH == 0) {
//            System.out.println("LA");
//            mobiles = repository.findByLastNameAndAddress(lastName, address);
//        } else if (firstName.equals("example") && address.equals("example") && phoneNumberH == 0) {
//            System.out.println("LM");
//            mobiles = repository.findByLastNameAndPhoneNumberM(lastName, phoneNumberM);
//        } else if (firstName.equals("example") && address.equals("example") && phoneNumberM == 0) {
//            System.out.println("LH");
//            mobiles = repository.findByLastNameAndPhoneNumberH(lastName, phoneNumberH);
//        } else if (firstName.equals("example") && lastName.equals("example") && phoneNumberH == 0) {
//            System.out.println("AM");
//            mobiles = repository.findByAddressAndPhoneNumberM(address, phoneNumberM);
//        } else if (firstName.equals("example") && lastName.equals("example") && phoneNumberM == 0) {
//            System.out.println("AH");
//            mobiles = repository.findByAddressAndPhoneNumberH(address, phoneNumberH);
//        } else if (firstName.equals("example") && lastName.equals("example") && address.equals("example")) {
//            System.out.println("MH");
//            mobiles = repository.findByPhoneNumberMAndPhoneNumberH(phoneNumberM, phoneNumberH);
//        } else if (phoneNumberM == 0 && phoneNumberH == 0) {
//            System.out.println("FLA");
//            mobiles = repository.findByFirstNameAndLastNameAndAddress(firstName, lastName, address);
//        } else if (address.equals("example") && phoneNumberH == 0) {
//            System.out.println("FLM");
//            mobiles = repository.findByFirstNameAndLastNameAndPhoneNumberM(firstName, lastName, phoneNumberM);
//        } else if (address.equals("example") && phoneNumberM == 0) {
//            System.out.println("FLH");
//            mobiles = repository.findByFirstNameAndLastNameAndPhoneNumberH(firstName, lastName, phoneNumberH);
//        } else if (lastName.equals("example") && phoneNumberH == 0) {
//            System.out.println("FAM");
//            mobiles = repository.findByFirstNameAndAddressAndPhoneNumberM(firstName, address, phoneNumberM);
//        } else if (lastName.equals("example") && phoneNumberM == 0) {
//            System.out.println("FAH");
//            mobiles = repository.findByFirstNameAndAddressAndPhoneNumberH(firstName, address, phoneNumberH);
//        } else if (lastName.equals("example") && address.equals("example")) {
//            System.out.println("FMH");
//            mobiles = repository.findByFirstNameAndPhoneNumberMAndPhoneNumberH(firstName, phoneNumberM, phoneNumberH);
//        } else if (firstName.equals("example") && address.equals("example")) {
//            System.out.println("LMH");
//            mobiles = repository.findByLastNameAndPhoneNumberMAndPhoneNumberH(lastName, phoneNumberM, phoneNumberH);
//        } else if (lastName.equals("example") && firstName.equals("example")) {
//            System.out.println("AMH");
//            mobiles = repository.findByAddressAndPhoneNumberMAndPhoneNumberH(address, phoneNumberM, phoneNumberH);
//        } else if (firstName.equals("example") && phoneNumberM == 0) {
//            System.out.println("LAM");
//            mobiles = repository.findByLastNameAndAddressAndPhoneNumberM(lastName, address, phoneNumberM);
//        } else if (firstName.equals("example") && phoneNumberH == 0) {
//            System.out.println("LAH");
//            mobiles = repository.findByLastNameAndAddressAndPhoneNumberH(lastName, address, phoneNumberH);
//        } else if (phoneNumberH == 0) {
//            System.out.println("FLAM");
//            mobiles = repository.findByFirstNameAndLastNameAndAddressAndPhoneNumberM(firstName, lastName, address, phoneNumberM);
//        } else if (phoneNumberM == 0) {
//            System.out.println("FLAH");
//            mobiles = repository.findByFirstNameAndLastNameAndAddressAndPhoneNumberH(firstName, lastName, address, phoneNumberH);
//        } else if (address.equals("example")) {
//            System.out.println("FLMH");
//            mobiles = repository.findByFirstNameAndLastNameAndPhoneNumberMAndPhoneNumberH(firstName, lastName, phoneNumberM, phoneNumberH);
//        } else if (lastName.equals("example")) {
//            System.out.println("FAMH");
//            mobiles = repository.findByFirstNameAndAddressAndPhoneNumberMAndPhoneNumberH(firstName, address, phoneNumberM, phoneNumberH);
//        } else if (firstName.equals("example")) {
//            System.out.println("LAMH");
//            mobiles = repository.findByLastNameAndAddressAndPhoneNumberMAndPhoneNumberH(lastName, address, phoneNumberM, phoneNumberH);
//        } else {
//            mobiles = repository.findByFirstNameAndLastNameAndAddressAndPhoneNumberMAndPhoneNumberH(firstName, lastName, address, phoneNumberM, phoneNumberH);
//        }
//        return mobiles;
//    }
//
//    @PutMapping("/mobiles/{id}")
//    public ResponseEntity<Mobile> updateCustomer(@PathVariable("id") long phoneNumberM, @RequestBody Mobile mobile) {
//        System.out.println("Update Mobile with phoneNumberM = " + phoneNumberM + "...");
//        System.out.println("Update MobM = " + phoneNumberM + "...");
//        Optional<Mobile> mobileData = repository.findById(phoneNumberM);
//        System.out.println("Update MobM = " + phoneNumberM + "...");
//
//
//        if (mobileData.isPresent()) {
//            System.out.println("DELO POSLO 1");
//            Mobile mobile1 = mobileData.get();
//            System.out.println("DELO POSLO 2");
//            mobile1.setFirstName(mobile.getFirstName());
//            System.out.println("DELO POSLO 3");
//            mobile1.setLastName(mobile.getLastName());
//            System.out.println("DELO POSLO 4");
//            mobile1.setAddress(mobile.getAddress());
//            System.out.println("DELO POSLO 5");
//            System.out.println("DELO POSLO 6");
//            mobile1.setPhoneNumberH(mobile.getPhoneNumberH());
//            System.out.println("DELO POSLO 7");
//            mobile1.setAdded(mobile.getAdded());
//            System.out.println("DELO POSLO 8");
//            return new ResponseEntity<>(repository.save(mobile1), HttpStatus.OK);
//        } else {
//            System.out.println("ERROR");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//}
