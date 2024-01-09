package com.example.elm_springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.elm_springboot.VO.Exception.AuthException;
import com.example.elm_springboot.dao.UserDao;
import com.example.elm_springboot.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Component
public class TokenUtils {

    private static final String TOKEN_SECRET_KEY = "tokenKEY";
    private static final String RefreshTOKEN_SECRET_KEY = "refreshTokenKEY";

    private static UserDao staticUserDao;
    @Resource
    UserDao userDao;
    @PostConstruct
    public void setUserService(){
        staticUserDao = userDao;
    }

    public static String createToken(String userId,String sign){
        return JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetMinute(new Date(),30))
                .sign(Algorithm.HMAC256(sign+TOKEN_SECRET_KEY)); //密匙
    }

    public static String createRefreshToken(String userId,String sign){
        return JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
                .sign(Algorithm.HMAC256(sign+RefreshTOKEN_SECRET_KEY)); //密匙
    }

    public static void verify(String token, HttpServletResponse response) throws AuthException, IOException {
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException e){
            throw new AuthException();
        }
        User user = staticUserDao.findUserByUserId(Long.parseLong(userId));
        if (user == null) throw new AuthException();
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword()+TOKEN_SECRET_KEY)).build();
        try {
            jwtVerifier.verify(token);
        }catch (TokenExpiredException te){
            if (verifyRefreshToken(user.getRefreshToken())){
                String token1 = createToken(userId, user.getPassword());
                String refreshToken = createRefreshToken(userId, user.getTelephone());
                user.setToken(token1);
                user.setRefreshToken(refreshToken);
                staticUserDao.save(user);

                String jsonResponse = String.format("{\"token\": \"%s\", \"refreshToken\": \"%s\"}", token1, refreshToken);
                response.getWriter().write(jsonResponse);
                response.setStatus(HttpStatus.OK.value());
                response.setContentType("application/json");
                response.getWriter().flush();
                response.getWriter().close();
            }
        }catch (JWTVerificationException e){
            throw new AuthException();
        }
    }

    public static boolean verifyRefreshToken(String RefreshToken) throws AuthException {
        String userId;
        try {
            userId = JWT.decode(RefreshToken).getAudience().get(0);
        }catch (JWTDecodeException e){
            throw new AuthException();
        }
        User user = staticUserDao.findUserByUserId(Long.parseLong(userId));
        if (user == null) throw new AuthException();
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getTelephone()+RefreshTOKEN_SECRET_KEY)).build();
        try {
            jwtVerifier.verify(RefreshToken);
        } catch (JWTVerificationException e){
            throw new AuthException();
        }
        return true;
    }


    public static User getCurrentUser(){
        try {
            HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserDao.findUserByUserId(Long.valueOf(userId));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
