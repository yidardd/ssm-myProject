package com.test;

import com.alibaba.fastjson.JSONObject;
import com.txn.dto.User;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2019/10/17 19:40
 * @Version 1.0
 * @Description MainTest
 */
public class MainTest {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("aaa");
        user.setPassword("123");
        System.out.println(JSONObject.toJSONString(user));
    }

}
