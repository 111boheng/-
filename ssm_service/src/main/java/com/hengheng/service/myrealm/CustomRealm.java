package com.hengheng.service.myrealm;

import com.hengheng.domain.Role;
import com.hengheng.domain.Users;
import com.hengheng.service.IUsersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class CustomRealm extends AuthorizingRealm {
    private Users user = null;
    @Autowired
    private IUsersService usersService;
//
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        List<Role> roles = getUserByUserName(username).getRoles();
        Set<String> set = new HashSet<>();
        for (Role role : roles) {
            System.out.println(role.getRoleName());
            set.add(role.getRoleName());
        }

                System.out.println(username+"......."+roles+"-----"+set);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(set);

        return authorizationInfo;
    }



    /**
     * 认证
     * @param authenticationToken 主体传过来的认证信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = (String) authenticationToken.getPrincipal();
//        String password = token.getPassword();
        String password = getUserByUserName(username).getPassword();
        System.out.println("realm++++++password++++:"+password);
        if(password == null)return null;
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,"customRealm");
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,credentialsSalt, getName());


        return authenticationInfo;
    }

    private Users getUserByUserName(String username) {
        user = usersService.findByUserName(username);
        return user;
    }

    public static void main(String[] args) {
        SimpleHash simpleHash = new SimpleHash("md5","654321","mark",1);
        System.out.println(simpleHash);
    }
}
