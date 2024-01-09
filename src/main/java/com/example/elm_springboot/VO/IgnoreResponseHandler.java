package com.example.elm_springboot.VO;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseHandler {

}
