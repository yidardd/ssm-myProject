package com.txn.config.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.apache.ibatis.annotations.Select;

public class MyInvocationHandler implements InvocationHandler {

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //sql
    Select annotation = method.getAnnotation(Select.class);
    System.out.println(annotation.value()[0]);

    return null;
  }
}
