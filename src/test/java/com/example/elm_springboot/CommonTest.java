package com.example.elm_springboot;

import com.example.elm_springboot.config.FileHandler.FileHandler;
import com.example.elm_springboot.utils.TokenUtils;
import com.example.elm_springboot.VO.ResponseStatusEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CommonTest {

    @Resource
    private FileHandler fileHandler;

    @Test
    void testUploadImageFile() {
        // 创建一个模拟的图片文件
        MockMultipartFile imageFile = new MockMultipartFile(
                "file",
                "image.jpg",
                "image/jpeg",
                "Mock image content".getBytes());

        // 调用上传方法
        String result = fileHandler.uploadFile(imageFile);

        System.out.println("Image File Path: " + result);

        fileHandler.test();
    }

    @Test
    void printsome(){
        System.out.println(File.separator);
    }

    @Test
    void printsome2(){
        System.out.println(ResponseStatusEnum.SUCCESS.getClass());
    }


    @Test
    void printsome3(){
        String versionSpring = SpringVersion.getVersion();

        String versionSpringBoot = SpringBootVersion.getVersion();

        System.out.println("Spring Version：" + versionSpring);

        System.out.println("SpringBoot Version：" + versionSpringBoot);
    }

    @Test
    void printsome4(){
        System.out.println(TokenUtils.createToken("123","123"));
    }
}
