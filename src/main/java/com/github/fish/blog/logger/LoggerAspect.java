package com.github.fish.blog.logger;


import com.github.fish.blog.base.api.entity.SystemLog;
import com.github.fish.blog.utils.JsonUtil;
import com.github.fish.blog.utils.WebUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);


    @Pointcut("execution(* com.github.fish.blog..controller.*.*(..))")
    public void loggerAop() {

    }

    @Before("loggerAop()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String realIP = WebUtil.getRequestRealIP(request);
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        String name = joinPoint.getSignature().getName();
        String classMethod = typeName + name;
        logger.info("-------IP--------:{}", realIP);
        logger.info("-------classMethod--------:{}", classMethod);

        SystemLog log = new SystemLog();
        log.setRealIp(realIP);

        logger.info("-------SystemLog--------:{}", JsonUtil.obj2Json(log));


    }

    @After("loggerAop()")
    public void doAfter(/*JoinPoint joinPoint*/) {
        logger.info("-------doAfter--------");
    }


    @AfterThrowing(pointcut = "loggerAop()", throwing = "e")
    public void doAfterThrowing(/*JoinPoint joinPoint,*/ Throwable e) {

    }

    /**
     * 方法返回的结果
     */
    @AfterReturning(returning = "result", pointcut = "loggerAop()")
    public void AfterReturning(Object result/*, JoinPoint joinPoint*/) {
//        logger.debug("--------------joinPoint 1----------------");
//        logger.debug("JoinPoint Target:{}", joinPoint.getTarget());
//        logger.debug("JoinPoint Signature:{}", joinPoint.getSignature());
//        logger.debug("JoinPoint StaticPart:{}", joinPoint.getStaticPart());
//        logger.debug("JoinPoint Args:{}", joinPoint.getArgs());
//        logger.debug("JoinPoint Kind:{}", joinPoint.getKind());
//        logger.debug("JoinPoint This:{}", joinPoint.getThis());
//        logger.debug("--------------joinPoint 2----------------");
        logger.info("returning : {}", result);
    }


}
