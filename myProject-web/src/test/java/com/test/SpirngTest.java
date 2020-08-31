package com.test;

import java.io.IOException;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import com.txn.config.mybatis.AppConfig;
import com.txn.mapper.DwRoleMapper;
import com.txn.mapper.TbStudentDAO;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-06-21 16:36
 * @Version 1.0
 * @Description SpirngTest
 */
public class SpirngTest {

  @Test
  public void test() {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    TbStudentDAO user = (TbStudentDAO) applicationContext.getBean("tbStudentDAO");
    DwRoleMapper user1 = (DwRoleMapper) applicationContext.getBean("dwRoleMapper");

    System.out.println(user.query());
    System.out.println(user1.query());

//    Object o = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{TbStudentDAO.class}, new MyInvocationHandler());
//    TbStudentDAO bean = (TbStudentDAO) o;
//    TbStudentDAO bean = applicationContext.getBean(TbStudentDAO.class);
//    List<Map<String, String>> query = bean.query();
//    System.out.println(query);


  }

  /**
   * Spring容器注入
   */
  private ResourceLoader resourceLoader;

  @Test
  public void test2() throws IOException {

    ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
    Resource[] resources = resolver.getResources("classpath*:your/package/name/**/*.class");

    for (Resource r : resources) {
      MetadataReader reader = metaReader.getMetadataReader(r);
      System.out.println(reader.getClassMetadata().getClassName());
    }

  }


}
