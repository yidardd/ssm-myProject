package com.txn.config.mybatis;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.context.annotation.Import;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Date 2020-07-18 16:35
 * @Description MyMapperScan
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportBeanDefinitionRegistrar.class)
public @interface MyMapperScan {

  String value();

}
