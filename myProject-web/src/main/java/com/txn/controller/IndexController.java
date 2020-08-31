package com.txn.controller;

import com.txn.common.ResponseObject;
import com.txn.util.JwtToken;
import com.txn.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-01-01 15:42
 * @Version 1.0
 * @Description IndexController
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseObject login(String username, String password) {
        ResponseObject responseObject = new ResponseObject();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            responseObject.success("登录失败");
            return responseObject;
        }
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        String usernamePasswordToken = JwtUtil.sign(username, password);
        SecurityUtils.getSubject().login(new JwtToken(usernamePasswordToken));
        responseObject.success(usernamePasswordToken);
        return responseObject;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseObject getUser() {
        ResponseObject responseObject = new ResponseObject();
        responseObject.success(true);
        CompletableFuture.runAsync(() -> {
            Subject subject = SecurityUtils.getSubject();
            subject.getSession().setAttribute("aaa", "aaa");
            boolean permitted = subject.isPermitted("user:list");
            System.out.println("-------" + permitted);
        });
        new Thread(() -> {
            Subject subject = SecurityUtils.getSubject();
            Object aaa = subject.getSession().getAttribute("aaa");
            boolean permitted = subject.isPermitted("user:list");
            System.out.println("-------" + permitted);
        }).start();
        return responseObject;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseObject user() {
        ResponseObject responseObject = new ResponseObject();
        Subject subject = SecurityUtils.getSubject();
        boolean permitted = subject.isPermitted("user:list");
        responseObject.success(permitted);
        return responseObject;
    }

    @RequestMapping(value = "/user2", method = RequestMethod.GET)
    public ResponseObject user2() {
        ResponseObject responseObject = new ResponseObject();
        Subject subject = SecurityUtils.getSubject();
        boolean permitted = subject.isPermitted("user:list2");
        responseObject.success(permitted);
        return responseObject;
    }

    @RequestMapping(value = "/unauthorize", method = RequestMethod.GET)
    public ResponseObject unauthorize() {
        ResponseObject responseObject = new ResponseObject();
        responseObject.failed("没有权限");
        responseObject.setStatus(401);
        return responseObject;
    }


}
