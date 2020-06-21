package com.hengheng.controller;

import com.hengheng.domain.SysLog;
import com.hengheng.domain.Users;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    private Date visitTime;//访问时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    //前置通知  主要是获取开始时间 ，执行的是哪个类，执行的是哪个方法
    @Before("execution(* com.hengheng.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//访问的时间
        clazz = jp.getTarget().getClass();//jp.getTarget()获取被代理对象
        String methodName = jp.getSignature().getName();//获取访问的方法的名字.joinpoint.getSignature():(signature是信号,标识的意思):获取被增强的方法相关信息
        Object[] args = jp.getArgs();//获取访问方法的参数

        //获取具体的访问的方法
        if(args == null || args.length == 0){//无参数
            method = clazz.getMethod(methodName);//获取无参数的方法
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0;i<args.length;i++){

                classArgs[i] = args[i].getClass();

            }
            method = clazz.getMethod(methodName, classArgs);//getMethod(String, Class<?>...)
        }


    }
    @After("execution(* com.hengheng.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        //访问的时长
        Long totalTime = new Date().getTime() - visitTime.getTime();

        String url = "";
        //clazz访问的类；method访问的方法；clazz != LogAop.class 访问的类不是LogAop
        if(clazz != null&&method != null&&clazz != LogAop.class){
            //获取类上的注解
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            //获取注解中的值@RequestMapping("/findUserByIdAndAllRole.do")中的值
            String[] clazzUrl = clazzAnnotation.value();
            //获取方法上的注解
            RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
            String[] methodUrl = methodAnnotation.value();

            url = clazzUrl[0]+methodUrl[0];
        }

        //获取当前的访问用户，因我是用的是shiro所以在realm中曾经获取过用户，可以参考
        String username = (String) SecurityUtils.getSubject().getPrincipal();


        //获取访问的ip，要使用request对象，但是首先要在web.xml中配置request的过滤器RequestContextListener，这样在 本类中就可以注入一个 request的对象
        //@Autowired
        //    private HttpServletRequest request;
        //就可以使用request对象了
        String ip = request.getRemoteAddr();

        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(totalTime);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);
        System.out.println(sysLog);



    }
}
