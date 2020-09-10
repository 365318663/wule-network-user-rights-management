package cn.itcast.controller;

import cn.itcast.domain.SysLog;
import cn.itcast.domain.UserInfo;
import cn.itcast.service.ISysLogService;
import cn.itcast.util.DateUtils;
import cn.itcast.util.UuidUtil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class LogAop {
    private Date visitTime;
    private Class clazz;
    private Method method;
    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    HttpServletRequest request;

    //前置通知
    @Before("execution(* cn.itcast.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if(args==null||args.length==0) {
            method = clazz.getMethod(methodName);
        }else {
            Class[] classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classes);
        }

    }

    @After("execution(* cn.itcast.controller.*.*(..))")
    public void doAfer(JoinPoint jp){
        long time = new Date().getTime()-visitTime.getTime();

        String url ="";
        if (clazz!=null&&method!=null&&clazz!= LogAop.class){
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(clazzAnnotation!=null){
                String classValue = clazzAnnotation.value()[0];
                url +=classValue;

                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String methodValue = methodAnnotation.value()[0];
                    url += methodValue;
                    String ip=request.getRemoteAddr();

                    SecurityContext context = SecurityContextHolder.getContext();
                    User user  = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    SysLog log = new SysLog();
                    log.setExecutionTime(time);
                    log.setId(UuidUtil.getUuid());
                    log.setIp(ip);
                    log.setMethod("[类]"+clazz.getName()+" [方法]"+method.getName());
                    log.setUrl(url);
                    log.setUsername(username);
                    log.setVisitTime(visitTime);

                    sysLogService.save(log);

                }
            }
        }


    }

}
