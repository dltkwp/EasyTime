package com.xianqu.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MyExceptionHandler implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        if (ex instanceof UnauthenticatedException) {
            attributes.put("code", 1000001);
            attributes.put("msg", "token错误");
        } else if (ex instanceof UnauthorizedException) {
            attributes.put("code", 1000002);
            attributes.put("msg", "用户无权限");
        } else if(ex instanceof IncorrectCredentialsException){
            attributes.put("code", 1000003);
            attributes.put("msg", "密码错误");
        } else if (ex instanceof LockedAccountException) {
            attributes.put("code", 1000004);
            attributes.put("msg", "登录失败，该用户已被冻结");
        } else if (ex instanceof AuthenticationException) {
            attributes.put("code", 1000005);
            attributes.put("msg", "该用户不存在");
        } else {
            attributes.put("code", 1000500);
            attributes.put("msg", ex.getMessage());
        }
        log.error("-----------------error---------------",ex);

        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }
}
