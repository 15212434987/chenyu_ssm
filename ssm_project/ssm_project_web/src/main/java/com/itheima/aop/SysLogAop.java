package com.itheima.aop;

import com.itheima.controller.SysLogController;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
public class SysLogAop {

    private String url;
    private Long executionTime;
    private Date vistTime;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    public void pid(){}

    //前置通知：获取访问当前系统时间
    @Before("pid()")
    public void beforeMethod(){
         vistTime=new Date();
    }


    //最终通知
    @After("pid()")
    public void afterMethod(JoinPoint obj) throws Exception {
        //1.获取执行controller对象的字节码
        Class objClass=obj.getTarget().getClass();
        //2.获取执行的方法名
        String mthodName=obj.getSignature().getName();
        //3.获取执行方法所带有的参数列表
        Object[] args = obj.getArgs();
        System.out.println("args数组:"+ Arrays.toString(args));
        if(!(obj.getTarget() instanceof SysLogController)){
            //1.判断指定类上是否存在注解
            RequestMapping classAnnotation = (RequestMapping)objClass.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValues = classAnnotation.value();
                System.out.println("controller类上注解值："+classValues[0]);
                //获取方法上的注解值
                //1.获取方法对象
                Method method=null;
                //1.1没有参数
                if(args==null){
                    method=objClass.getMethod(mthodName);
                }else{
                    //1.2有参数
                    Class[] classArgs=new Class[args.length];
                    //参数类型转换
                    for (int i=0;i<args.length;i++){
                        if(args[i] instanceof BindingAwareModelMap){
                            classArgs[i]= Model.class;
                        }else {
                            classArgs[i] = args[i].getClass();
                        }
                    }
                    method=objClass.getMethod(mthodName,classArgs);
                }
                //2.获取方法上的注解
                String[] methodValue=null;
                RequestMapping methodAnnotation= method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    //3.获取注解的值
                    methodValue = methodAnnotation.value();
                    System.out.println("method上注解值："+methodValue[0]);
                }
                url=classValues[0]+methodValue[0];
            }
            System.out.println("请求的URL："+url);
            //获取执行的时长
            executionTime= new Date().getTime()-vistTime.getTime();
            //获取请求的IP地址
            String ip = request.getRemoteAddr();
            //获取操作者的名称（登陆者）
            UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = user.getUsername();


            //将获取的信息存入SysLog对象
            SysLog sysLog=new SysLog();
            sysLog.setVisitTime(vistTime);
            sysLog.setUsername(username);
            sysLog.setIp(ip);
            sysLog.setUrl(url);
            sysLog.setExecutionTime(executionTime);
            sysLog.setMethod(mthodName);

            sysLogService.save(sysLog);
        }
    }
}






