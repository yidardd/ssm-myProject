package com.txn.config;

import com.txn.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("-doGetAuthenticationInfo登录认证-");
        if ("admin".equals(token.getPrincipal())) {
            User user = new User();
            user.setId(1);
            user.setUserName("admin");
            user.setPassword("admin");
//            user.setPassword("admin");
            ByteSource bytes = ByteSource.Util.bytes("1");
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), bytes, this.getName());

            return simpleAuthenticationInfo;
        }
        return null;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行doGetAuthorizationInfo方法进行授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("role_admin");
        info.addStringPermission("user:add");
        info.addStringPermission("user:list");
        return info;
    }


}