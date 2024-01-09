package com.example.elm_springboot.config.FileHandler;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class FileHandler {

    @org.springframework.beans.factory.annotation.Value("${path.root}")
    private String rootPath;

    @org.springframework.beans.factory.annotation.Value("${path.img}")
    private String imgFolderPath;

    @org.springframework.beans.factory.annotation.Value("${path.video}")
    private String videoFolderPath;

    public String uploadFile(MultipartFile mf) {
        try {
            String fileName = mf.getOriginalFilename();
            String contentType = mf.getContentType();

            if (isImage(contentType)) {
                return saveFile(mf, imgFolderPath);
            } else if (isVideo(contentType)) {
                return saveFile(mf, videoFolderPath);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void test(){
        System.out.println(rootPath);
        System.out.println(imgFolderPath);
        System.out.println(videoFolderPath);
    }

    private String saveFile(MultipartFile mf, String folderPath) throws IOException {
        String fileName = mf.getOriginalFilename();
        Date d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String fileNewName = Long.toString(d.getTime()) + new Random().nextInt(8999) + 1000 +
                fileName.substring(fileName.lastIndexOf("."));

        String filePath = rootPath + folderPath + File.separator + File.separator + fileNewName;
        File fileDir = new File(rootPath + folderPath + File.separator );
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        File file = new File(filePath);
        mf.transferTo(file);
        return (folderPath  + File.separator + fileNewName).replace("\\", "/");
    }

    private boolean isImage(String contentType) {
        return contentType != null && contentType.startsWith("image");
    }

    private boolean isVideo(String contentType) {
        return contentType != null && contentType.startsWith("video");
    }
}

