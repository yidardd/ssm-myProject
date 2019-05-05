package com.txn.controller;

import com.txn.common.ResponseObject;
import com.txn.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public ResponseObject getUserId(HttpServletRequest request, Model model) {
        ResponseObject responseObject = new ResponseObject();
        String test = testService.test();
        log.error("aaa");
        log.info("aaa");
        responseObject.success("ok");
        return responseObject;
    }


}
