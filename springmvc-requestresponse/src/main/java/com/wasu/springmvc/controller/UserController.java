package com.wasu.springmvc.controller;

import com.wasu.springmvc.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author ZHANGLEI
 * @date 2021/11/28 11:04
 */
@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/responsePrint")
    public void responsePrint(HttpServletResponse httpResponse) {
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setId(1001l);
        try {
            httpResponse.getWriter().print(user);
        } catch(IOException e) {
            logger.error(e.getMessage());
        }
    }

    @RequestMapping("/responseBody")
    @ResponseBody
    public User responseBody() {
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setId(1001l);
        return user;
    }

    @RequestMapping("/testAxios")
    @ResponseBody
    public String testAxios(User user) {
        logger.info("username: {}", user.getUsername());
        return "Hell, Axios";
    }

    @RequestMapping("testDownload")
    public ResponseEntity<byte[]> testDownload(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/frog.jpg");
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }

    @RequestMapping("testUpload")
    public String testUpload(MultipartFile multipartFile, HttpSession session) throws IOException {
        //获取上传的文件的文件名
        String fileName = multipartFile.getOriginalFilename();
        logger.info("fileName: {}", fileName);
        //处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;
        //获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("upload");
        File file = new File(photoPath);
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        //实现上传功能
        multipartFile.transferTo(new File(finalPath));
        return "success";
    }

    @RequestMapping("testException")
    public String testException() {
        int i = 1/0;
        return "success";
    }
}
