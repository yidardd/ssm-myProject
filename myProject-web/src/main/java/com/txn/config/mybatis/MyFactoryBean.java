package com.txn.config.mybatis;

import java.lang.reflect.Proxy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-06-21 18:57
 * @Version 1.0
 * @Description MyFactoryBean
 */
//@Component
public class MyFactoryBean implements FactoryBean {

  private Class mapperInterfiace;

  public MyFactoryBean(Class mapperInterfiace) {
    this.mapperInterfiace = mapperInterfiace;
  }

  @Override
  public Object getObject() throws Exception {
    Object user = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{mapperInterfiace}, new MyInvocationHandler());
    return user;
  }

  @Override
  public Class<?> getObjectType() {
    return User.class;
  }

  @Override
  public boolean isSingleton() {
    return false;
  }
}
