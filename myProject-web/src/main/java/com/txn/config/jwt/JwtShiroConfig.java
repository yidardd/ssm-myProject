package com.txn.config.jwt;

import com.txn.config.ShiroRealm;
import com.txn.config.ShiroSessionListener;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-01-01 15:05
 * @Version 1.0
 * @Description ShiroConfig
 */
@Configuration
public class JwtShiroConfig {

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();

        //session管理
//        webSecurityManager.setSessionManager(sessionManager());

        //realm管理
        webSecurityManager.setRealm(realm());

        //缓存管理
        webSecurityManager.setCacheManager(new MemoryConstrainedCacheManager());
        //使用ehcache
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManager(getEhCacheManager());
//        webSecurityManager.setCacheManager(ehCacheManager);

        //redis实现
//        webSecurityManager.setCacheManager(redisCacheManager());

        //关闭session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        webSecurityManager.setSubjectDAO(subjectDAO);

        return webSecurityManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {

        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost:6379");
        redisManager.setDatabase(1);
        redisManager.setTimeout(5000);
//        redisManager.setPassword();

        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }

    @Bean
    public CacheManager getEhCacheManager() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("classpath:org/apache/shiro/cache/ehcache/ehcache.xml"));
        return ehCacheManagerFactoryBean.getObject();
    }

    @Bean
    public Realm realm() {
        JwtShiroRealm shiroRealm = new JwtShiroRealm();
        return shiroRealm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorize");
        shiroFilterFactoryBean.setFilterChainDefinitions(
                "/login = anon\n" +
                        "/logout = logout\n" +
                        "/user = jwt,authc,perms[user:list]\n" +
                        "/user2 = jwt,authc,perms[user:list2]\n" +
                        "/** = jwt\n" +
                        "");

        HashMap<String, Filter> myFIleter = new HashMap<>();
        myFIleter.put("jwt", new JwtFilter());

        shiroFilterFactoryBean.setFilters(myFIleter);

        return shiroFilterFactoryBean;
    }

}
