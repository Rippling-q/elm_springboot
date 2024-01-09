package com.example.elm_springboot.other.Hello;

import com.example.elm_springboot.config.jwt.AuthAccess;
import com.example.elm_springboot.VO.Exception.AppException;
import com.example.elm_springboot.VO.IgnoreResponseHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
@CrossOrigin("*")
public class hello {
    @AuthAccess
    @GetMapping("/sayHello/{name}")
    public String Hello(@PathVariable String name){
        return "hello "+name;
    }

    @GetMapping("/sayHelloString/{name}")
    @IgnoreResponseHandler
    public String HelloString(@PathVariable String name){return "hello"+name;}

    @GetMapping("/errorTest")
    public String exceptionTest() throws AppException {
        if (1!=2){
            throw new AppException();
        }
       else {
           return "ok";
        }
    }
}
