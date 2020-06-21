package com.hengheng.service.myrealm;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class roleOrFilter extends AuthorizationFilter {
    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param o                 传过来的权限或者角色
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        //获取主体
        Subject subject = getSubject(servletRequest,servletResponse);
        String[] roles = (String[]) o;
        if(roles == null)return true;//为空说明都可以访问
        for (String role : roles) {
            if(subject.hasRole(role)){
                return true;
            }
        }
        return false;
    }
}
