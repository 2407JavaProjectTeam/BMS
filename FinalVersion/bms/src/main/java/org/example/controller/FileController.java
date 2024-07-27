package org.example.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileController {
    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public SaResult uploadFile(MultipartFile file){
        String path = "D:/upload/";
        String fileName = file.getOriginalFilename();
        String s = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String newFileName = System.currentTimeMillis()+s;
        File dir = new File(path+newFileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        try {
            file.transferTo(dir);
            //将上传的头像地址存入数据库中
            String loginId = (String) StpUtil.getLoginId();
            userService.updateUserImg(loginId,"/images/"+newFileName);
            return SaResult.get(200,"上传成功！","/images/"+newFileName);
        } catch (IOException e) {
            return SaResult.error("上传失败！");
        }
    }
}
