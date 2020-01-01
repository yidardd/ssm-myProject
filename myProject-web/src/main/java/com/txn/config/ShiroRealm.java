package com.txn.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.Map;

public class ShiroRealm extends AuthorizingRealm {

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("-权限校验-");
        if ("admin".equals(token.getPrincipal())) {
            Map<String, Object> user = new HashMap<>();
            user.put("username", "admin");
            user.put("pass", "admin");
            user.put("user_id", 1);
            ByteSource salt = ByteSource.Util.bytes("abcd123");
            return new SimpleAuthenticationInfo(user, user.get("username"), this.getName());
        }
        return null;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("role_admin");
        info.addStringPermission("user:add");
        info.addStringPermission("user:list");
        return info;
    }


}