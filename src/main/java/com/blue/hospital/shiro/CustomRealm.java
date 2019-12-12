package com.blue.hospital.shiro;

import com.blue.hospital.entity.User;
import com.blue.hospital.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.findUserByName(userName);
        if (user==null){
           throw new UnknownAccountException("用户不存在");
        }else{
            return new SimpleAuthenticationInfo(userName,user.getPassWord(),getName());

        }



    }
}
