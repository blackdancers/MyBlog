package com.github.fish.common.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * @ControllerAdvice 注解会拦截所有标注有@Controller的控制器
 */

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    /**
     * 拦截异常，并处理
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        logger.error("Request URL : {}, Exception: {}", request.getRequestURL(), exception);
        //logger.error("系统运行中处理{}发生异常, 错误信息:{}", request.getRequestURL().toString(), exception);
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", exception);

        /*注解*/
        if (null != AnnotationUtils.findAnnotation(exception.getClass(),ResponseStatus.class)){
            throw exception;
        }
        //定位错误页面
        mv.setViewName("error/error");

        return mv;
    }


}
































