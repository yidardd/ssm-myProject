package com.txn.config.mybatis;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Date 2020-07-18 16:19
 * @Description MyImportBeanDefinitionRegistrar
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

    MultiValueMap<String, Object> allAnnotationAttributes = importingClassMetadata.getAllAnnotationAttributes("com.txn.config.mybatis.MyMapperScan");
    List<Object> value = allAnnotationAttributes.get("value");
    Set<Class> scan = ClassScaner.scan(value.get(0).toString(), null);
    for (Class aClass : scan) {
      BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MyFactoryBean.class);
      AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

      // 构造器传参
      beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(aClass.getName());
      registry.registerBeanDefinition(toLowerCaseFirstOne(aClass.getSimpleName()), beanDefinition);
    }

  }

  public static String toLowerCaseFirstOne(String s){
    if(Character.isLowerCase(s.charAt(0)))
      return s;
    else
      return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
  }

}
