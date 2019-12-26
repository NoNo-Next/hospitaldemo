package com.blue.hospital.shiro;

import com.blue.hospital.entity.User;
import com.blue.hospital.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
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
        //UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUserByName(userName);
        /*String salt = user.getSalt();
        String password =new String(token.getPassword());
        String passwordEncoded = new SimpleHash("md5",password,salt,2).toString();*/

        if (user==null){
           throw new UnknownAccountException("用户不存在");
        }/*else if ("".equals(password) && !password.equals(passwordEncoded)){
            throw new AuthenticationException("密码错误");
        }*/else{
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassWord(), ByteSource.Util.bytes(user.getSalt()), getName());
             Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user",user);
            return authenticationInfo;
        }



    }
}
