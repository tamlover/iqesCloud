package com.advantech.iqescloud.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class TestService {



    public void uploadPhoto(String jsonData){
//
//        MultipartFile file= JSON.parseObject(jsonData,MultipartFile.class);
//
//        String localPath = request.getSession().getServletContext().getRealPath("/photo/test");
//        String fileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();
//
//        File dir=new File(localPath);
//        if (!dir.exists()){
//            dir.mkdirs();
//        }
//
//        try {
//            file.transferTo(new File(localPath+"\\"+fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(request.getServletContext().getContextPath()+"/photo/test/"+fileName);
    }
}
