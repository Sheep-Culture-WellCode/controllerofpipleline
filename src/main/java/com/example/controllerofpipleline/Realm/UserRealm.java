package com.example.controllerofpipleline.Realm;

import com.example.controllerofpipleline.domin.User;
import com.example.controllerofpipleline.service.IShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm{
    @Autowired
    IShiroService shiroService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //给资源添加授权字符串
//        info.addStringPermission("add");
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        info.addStringPermission(user.getPerms());
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = shiroService.findByName(token.getUsername());
        System.out.println(user.toString());
        if (user == null){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getU_psw(),"");
    }
}
