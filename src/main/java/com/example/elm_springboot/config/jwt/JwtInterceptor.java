
package com.example.elm_springboot.config.jwt;

import cn.hutool.core.util.StrUtil;
import com.example.elm_springboot.utils.TokenUtils;
import com.example.elm_springboot.VO.Exception.AuthException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())){
            String requestHeader = request.getHeader("Access-Control-Request-Headers");
            if (requestHeader != null){
                response.addHeader("Access-Control-Request-Headers",requestHeader);
            }
            else response.addHeader("Access-Control-Allow-Origin","*");
            return true;
        }
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)){
            token = request.getParameter("token");
        }
        if (handler instanceof HandlerMethod){
            if (((HandlerMethod)handler).getMethodAnnotation(AuthAccess.class) != null){
                return true;
            }
        }
//        执行认证
        if(StrUtil.isBlank(token)){
            throw new AuthException();
        }
        TokenUtils.verify(token,response);
        return true;
    }
}



