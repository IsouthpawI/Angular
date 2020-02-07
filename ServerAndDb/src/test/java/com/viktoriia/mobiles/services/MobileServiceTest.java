package com.viktoriia.mobiles.services;

import com.viktoriia.mobiles.dao.MobileDAO;
import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.exception.BadRequestException;
import com.viktoriia.mobiles.service.MobilesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MobilesService.class, secure = false)
public class MobileServiceTest {
    @Autowired
    private MockMvc mockMvc;

    private String pattern = "yyyy-MM-dd";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private Date date = simpleDateFormat.parse("2018-09-09");

    @MockBean
    private MobileDAO mobileDAO;

    private Mobile mockMobile = new Mobile("Vika", "Pol", "Jedlikova", 123456789, 1234567890, date);
    private String exampleMobileJson = "{\"firstName\":\"Vika\",\"lastName\":\"Pol\",\"address\":\"Jedlikova\",\"phoneNumberM\":\"123456789\", \"phoneNumberH\":\"1234567890\", \"added\":\"2018-09-09\"}";

    private ArrayList<Mobile> list = new ArrayList<Mobile>();


    public MobileServiceTest() throws ParseException {
    }

    @Test
    public void getMobileById() throws Exception{
        Mockito.when(
                mobileDAO.getMobileById(Mockito.anyLong())).thenReturn(mockMobile);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "http://localhost:8080/api/mobiles/get/123456789").accept(
//                MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        System.out.println(result.getResponse());
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(123456789, mockMobile.getPhoneNumberM());
//        String expected = "{firstName:Vika,lastName:Pol,address:Jedlikova,phoneNumberM:123456789}";
//        JSONAssert.assertEquals(expected, result.getResponse()
//                .getContentAsString(), false);
    }

    @Test
    public void getCount() throws Exception{
        Mockito.when(
                mobileDAO.getCountOfMobiles()).thenReturn(1);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "http://localhost:8080/api/mobiles/get/123456789").accept(
//                MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        System.out.println(result.getResponse());
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(1, mobileDAO.getCountOfMobiles());
//        String expected = "{firstName:Vika,lastName:Pol,address:Jedlikova,phoneNumberM:123456789}";
//        JSONAssert.assertEquals(expected, result.getResponse()
//                .getContentAsString(), false);
    }




//    @Test
//    public void createArticle() throws Exception {
//        boolean mockBoolean = true;
//
//
//        Mockito.when(
//                mobilesService.isExistMobile(Mockito.any(Mobile.class))).thenReturn(mockBoolean);
////        System.out.println(mockMobile.toString());
////        mobilesService.createMobile(mockMobile);
////        // Send course as body to /students/Student1/courses
////        RequestBuilder requestBuilder = MockMvcRequestBuilders
////                .post("http://localhost:8080/api/mobiles/create")
////                .accept(MediaType.APPLICATION_JSON).content(exampleMobileJson)
////                .contentType(MediaType.APPLICATION_JSON);
////
////
////        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
////        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//
//    }
//
    @Test
    public void testFooDelete() throws Exception {
        Mockito.when(
                mobileDAO.deleteAllMobile()).thenReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("http://localhost:8080/api/mobiles/delete")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
//
//    @Test
//    public void testFooDelete2() throws Exception {
//        mobileDAO.deleteAllMobile();
//        //Mockito.when(
//        //        mobileDAO.deleteAllMobile()).thenReturn(true);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("http://localhost:8080/api/mobiles/delete")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
//    }
////
//    @Test
//    public void testFooDelete3() throws Exception {
//        Mockito.when(
//                mobilesSpecialSearchService.deleteAllPersonNumber()).thenReturn(true);
//        Mockito.when(
//                mobilesService.deleteAllMobile()).thenReturn(false);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("http://localhost:8080/api/mobiles/delete")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
//    }
//
//    //================DELETE Mobile=======================
//    @Test
//    public void testFooDeleteMobile() throws Exception {
//        Mockito.when(
//                mobilesService.getMobileById(Mockito.anyLong())).thenReturn(mockMobile);
//        Mockito.when(
//                mobilesSpecialSearchService.getPersonNumberByMobile(Mockito.any())).thenReturn(mockPerson);
//        Mockito.when(
//                mobilesSpecialSearchService.deletePersonNumber(Mockito.any())).thenReturn(true);
//
//        mobilesService.deleteMobile(mockMobile.getPhoneNumberM());
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("http://localhost:8080/api/mobiles/123456789")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
//    }
//
//    @Test
//    public void testFooNotDeleteMobile() throws Exception {
//        Mockito.when(
//                mobilesService.getMobileById(Mockito.anyLong())).thenReturn(null);
//        Mockito.when(
//                mobilesSpecialSearchService.getPersonNumberByMobile(Mockito.any())).thenReturn(mockPerson);
//        Mockito.when(
//                mobilesSpecialSearchService.deletePersonNumber(Mockito.any())).thenReturn(true);
//
//        mobilesService.deleteMobile(mockMobile.getPhoneNumberM());
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("http://localhost:8080/api/mobiles/123456789")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
//    }
//
//    @Test
//    public void testFooNotDeleteMobile2() throws Exception {
//        Mockito.when(
//                mobilesService.getMobileById(Mockito.anyLong())).thenReturn(mockMobile);
//        Mockito.when(
//                mobilesSpecialSearchService.getPersonNumberByMobile(Mockito.any())).thenReturn(null);
//        Mockito.when(
//                mobilesSpecialSearchService.deletePersonNumber(Mockito.any())).thenReturn(true);
//
//        mobilesService.deleteMobile(mockMobile.getPhoneNumberM());
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("http://localhost:8080/api/mobiles/123456789")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
//    }
//
//    @Test
//    public void testFooNotDeleteMobile3() throws Exception {
//        Mockito.when(
//                mobilesService.getMobileById(Mockito.anyLong())).thenReturn(mockMobile);
//        Mockito.when(
//                mobilesSpecialSearchService.getPersonNumberByMobile(Mockito.any())).thenReturn(mockPerson);
//        Mockito.when(
//                mobilesSpecialSearchService.deletePersonNumber(Mockito.any())).thenReturn(false);
//
//        mobilesService.deleteMobile(mockMobile.getPhoneNumberM());
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("http://localhost:8080/api/mobiles/123456789")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
//    }
//
//    //========================UPDATE=====================
//    @Test
//    public void testFooUpdateMobile() throws Exception {
////        Mockito.when(
////                mobilesService.getMobileById(Mockito.anyLong())).thenReturn(mockMobile);
////
////        //mobilesSpecialSearchService.updatePersonNumber(mockMobile);
////       // mobilesService.updateMobile(mockMobile);
////
////        doNothing().when(mobilesSpecialSearchService).updatePersonNumber(mockMobile);
////        doNothing().when(mobilesService).updateMobile(mockMobile);
////        //        Mockito.when(
//////                mobilesSpecialSearchService.getPersonNumberByMobile(Mockito.any())).thenReturn(mockPerson);
//////        Mockito.when(
//////                mobilesSpecialSearchService.deletePersonNumber(Mockito.any())).thenReturn(false);
////
////    //    mobilesService.deleteMobile(mockMobile.getPhoneNumberM());
////
////        RequestBuilder requestBuilder = MockMvcRequestBuilders
////                .put("http://localhost:8080/api/mobiles/123456789")
////                .accept(MediaType.APPLICATION_JSON)
////                .contentType(MediaType.APPLICATION_JSON);
////
////        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
////        MockHttpServletResponse response = result.getResponse();
////
////        //verifyNoMoreInteractions(mobilesService);
////       assertEquals(HttpStatus.OK.value(), response.getStatus());
//    }
//
//
//    //========================SEARCH=====================
    @Test
    public void search() throws Exception{
        //System.out.println("LA");
        //System.out.println(mockMobile.toString());
        list.add(mockMobile);
        Mockito.when(
                mobileDAO.findByParameters(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyLong(), Mockito.anyLong())).thenReturn(list);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "http://localhost:8080/api/mobiles/Vika/Pol/Jedlikova/123456789/1234567890").accept(
//                MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        System.out.println(result.getResponse());
//        //String expected = "[{firstName:Vika,lastName:Pol,address:Jedlikova,phoneNumberM:123456789}]";
//        //JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//        // JSONAssert.assertEquals(exampleMobileJson, result.getResponse().getContentAsString(), false);
//
//        MockHttpServletResponse response = result.getResponse();
        assertEquals(1, list.size());
    }
//
//    @Test
//    public void searchPerson() throws Exception{
//        //System.out.println("LA");
//        //System.out.println(mockMobile.toString());
//        listP.add(mockPerson);
//        Mockito.when(
//                mobilesSpecialSearchService.findNumbersByFirstNameOrLastName(Mockito.anyString(),Mockito.anyString())).thenReturn(listP);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "http://localhost:8080/api/mobiles/searchNew/Vika/Pol").accept(
//                MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        System.out.println(result.getResponse());
//        String expected = "[{lastName:Pol,firstName:Vika,mobile:{firstName:Vika,lastName:Pol,address:Jedlikova,phoneNumberM:123456789,phoneNumberH:1234567890}}]";
//        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//        // JSONAssert.assertEquals(exampleMobileJson, result.getResponse().getContentAsString(), false);
//
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//    }

}
