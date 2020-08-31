package com.txn.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.txn.service.TestService;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-05-23 16:20
 * @Version 1.0
 * @Description ApplicationContextTest
 */
public class ApplicationContextTest {

  public static void main(String[] args) {
    //启动spring容器
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    System.out.println("启动成功");

    TestService testService = (TestService)context.getBean("testService");
    System.out.println(testService.printStr());

  }

}
