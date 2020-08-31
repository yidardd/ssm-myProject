package com.txn.controller;

import com.txn.common.ResponseObject;
import com.txn.dto.User;
import com.txn.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by stack on 2019/5/5.
 */
@ResponseBody
@Controller
@Slf4j
public class TestController extends BaseController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseObject test(HttpServletRequest request, Model model) {
        ResponseObject responseObject = new ResponseObject();
        String test = testService.test();
        log.error("aaa");
        log.info("aaa");
        responseObject.success("ok");
        return responseObject;
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public ResponseObject test1(Integer i) {
        ResponseObject responseObject = new ResponseObject();
        log.error("aaa");
        log.info("aaa");
        responseObject.success("ok");
        return responseObject;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ResponseObject test2(@RequestParam("i") Integer i) {
        ResponseObject responseObject = new ResponseObject();
        log.error("aaa");
        log.info("aaa");
        responseObject.success("ok");
        return responseObject;
    }
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public ResponseObject test3(User user) {
        ResponseObject responseObject = new ResponseObject();
        log.error("aaa");
        log.info("aaa");
        responseObject.success("ok");
        return responseObject;
    }

    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public ResponseObject test4(@RequestParam("user") User user) {
        ResponseObject responseObject = new ResponseObject();
        log.error("aaa");
        log.info("aaa");
        responseObject.success("ok");
        return responseObject;
    }



}
