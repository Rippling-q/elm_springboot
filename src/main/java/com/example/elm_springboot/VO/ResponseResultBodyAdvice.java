package com.example.elm_springboot.VO;

import cn.hutool.json.JSONUtil;
import com.example.elm_springboot.VO.Exception.AppException;
import com.example.elm_springboot.VO.Exception.AuthException;
import com.example.elm_springboot.VO.Exception.MessageException;
import com.example.elm_springboot.VO.Exception.PayException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {

    @ExceptionHandler({AppException.class})
    public ResponseVO<?> handleBusinessException(AppException ex) {
        return ResponseVO.failure();
    }

    @ExceptionHandler({MessageException.class})
    public ResponseVO<?> MessageExceptionHandler(MessageException e){return ResponseVO.failure(ResponseStatusEnum.Message_ERROR,null);}

    @ExceptionHandler({PayException.class})
    public ResponseVO<?> PayExceptionHandler(PayException e){return ResponseVO.failure(ResponseStatusEnum.Pay_ERROR,"支付错误信息");}

    @ExceptionHandler({AuthException.class})
    public ResponseVO<?> AuthExceptionHandler(AuthException e){return ResponseVO.failure(ResponseStatusEnum.Auth_ERROR,"权限不足");}

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.hasMethodAnnotation(IgnoreResponseHandler.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        ResponseVO<?> respVo;
        if (body instanceof ResponseVO) {
            respVo = (ResponseVO<?>) body;
        }
        else {
            respVo = ResponseVO.success(body);
        }
        // 如果响应体为字符串类型，将其转换为 JSON 字符串
        if (body instanceof String) {
            return JSONUtil.toJsonStr(respVo);
        }
        return respVo;
    }

}

