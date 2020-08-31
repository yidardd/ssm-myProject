package com.txn.config.mybatis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-06-21 18:31
 * @Version 1.0
 * @Description MyBeanFactoryPostProcessor
 */
//@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//    configurableListableBeanFactory.registerSingleton();
    BeanDefinition user = configurableListableBeanFactory.getBeanDefinition("user");
    user.setBeanClassName("com.txn.config.mybatis.User2");

  }
}
