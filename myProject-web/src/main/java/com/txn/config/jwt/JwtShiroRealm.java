package com.txn.config.jwt;

import com.txn.dto.User;
import com.txn.util.JwtToken;
import com.txn.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

@Slf4j
public class JwtShiroRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("-doGetAuthenticationInfo登录认证-");

        String tokenStr = (String) token.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(tokenStr);
        log.info("登录的用户:" + username);


        if ("admin".equals(username)) {

            //数据库查出来的用户
            User user = new User();
            user.setId(1);
            user.setUserName("admin");
            user.setPassword("admin");
//            ByteSource bytes = ByteSource.Util.bytes("1");
            //验证密码是否正确
            if (JwtUtil.verify(tokenStr, username, user.getPassword())) {
                log.info("登录成功");
            } else {
                throw new UnknownAccountException("用户名密码错误");
            }
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token.getCredentials(), token.getCredentials(), this.getName());
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
        String username = JwtUtil.getUsername(principalCollection.toString());

        log.info("登录的用户:" + username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("role_admin");
        info.addStringPermission("user:add");
        info.addStringPermission("user:list");
        return info;
    }


}