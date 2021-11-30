package com.wasu.springmvc.controller;

import com.wasu.springmvc.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZHANGLEI
 * @date 2021/11/29 9:21
 */
@RestController
public class HelloController {
    @RequestMapping("/testRestController")
    public User testRestController() {
        User user = new User();
        user.setId(1l);
        user.setUsername("test1user");
        user.setSex(0);
        user.setEmail("test1user@test.com");
        return user;
    }
}
