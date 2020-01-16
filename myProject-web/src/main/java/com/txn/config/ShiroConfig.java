package com.txn.config;

import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-01-01 15:05
 * @Version 1.0
 * @Description ShiroConfig
 */
//@Configuration
public class ShiroConfig {

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

        return webSecurityManager;
    }

    /**
     * 配置会话管理器，设定会话超时及保存
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
        //配置监听
        listeners.add(new ShiroSessionListener());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setCacheManager(new MemoryConstrainedCacheManager());

        sessionManager.setGlobalSessionTimeout(10000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(5000);
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        return sessionManager;
    }

    @Bean("sessionIdCookie")
    public SimpleCookie sessionIdCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //maxAge=-1表示浏览器关闭时失效此Cookie
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    /**
     * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
     * MemorySessionDAO 直接在内存中进行会话维护
     * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     * @return
     */
    @Bean
    public SessionDAO sessionDAO() {

        //内存缓存
//        return new MemorySessionDAO();

        // ehcache
//        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManager(getEhCacheManager());
//        enterpriseCacheSessionDAO.setCacheManager(ehCacheManager);
//        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
//        enterpriseCacheSessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
//        return enterpriseCacheSessionDAO;

        //redis
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        RedisManager redisManager = new RedisManager();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;

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
        ShiroRealm shiroRealm = new ShiroRealm();

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashIterations(1);
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
//        hashedCredentialsMatcher.setHashAlgorithmName();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return shiroRealm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setFilterChainDefinitions(" /login = anon\n" +
                "                /logout = logout\n" +
                "/user = authc,perms[user:list]\n" +
                "                /** = authc\n" +
                "");

        return shiroFilterFactoryBean;
    }

}
