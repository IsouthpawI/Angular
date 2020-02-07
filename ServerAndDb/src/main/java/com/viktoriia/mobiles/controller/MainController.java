//package com.viktoriia.mobiles.controller;
//
//import com.viktoriia.mobiles.entity.Mobiles;
//import com.viktoriia.mobiles.model.MobilesInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:4200")
//@Controller
////@RestController
//@RequestMapping("/api")
//public class MainController {
////
////    @Autowired
////    private MobileDAO mobileDAO;
//
//    @Autowired
//    private IArticleService articleService;
//
//   // @RequestMapping(value = "/api/mobiles", method = RequestMethod.GET)
////    @GetMapping("/mobiles")
////    public List<MobilesInfo> showMobiles(Model model) {
////        System.out.println("GET ALL MOBILES");
////        List<MobilesInfo> list = mobileDAO.listMobileInfo();
//////        List<Mobiles> list = mobileDAO.listMobiles();
////
////       // model.addAttribute("accountInfos", list);
////
////        return list;
////    }
//
//    @GetMapping("/mobiles")
//    public ResponseEntity<List<Mobiles>> getAllArticles() {
//        List<Mobiles> list = articleService.getAllArticles();
//        return new ResponseEntity<List<Mobiles>>(list, HttpStatus.OK);
//    }
//
////    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
////    public String viewSendMoneyPage(Model model) {
////
////        SendMoneyForm form = new SendMoneyForm(1L, 2L, 700d);
////
////       // model.addAttribute("sendMoneyForm", form);
////
////        return "sendMoneyPage";
////    }
//
//
////    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
////    public String processSendMoney(Model model, SendMoneyForm sendMoneyForm) {
////
////        System.out.println("Send Money: " + sendMoneyForm.getAmount());
////
////        try {
////            mobileDAO.sendMoney(sendMoneyForm.getFromAccountId(), //
////                    sendMoneyForm.getToAccountId(), //
////                    sendMoneyForm.getAmount());
////        } catch (BankTransactionException e) {
////            model.addAttribute("errorMessage", "Error: " + e.getMessage());
////            return "/sendMoneyPage";
////        }
////        return "redirect:/";
////    }
//}
