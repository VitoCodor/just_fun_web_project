package com.vitoboy.funweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: vito
 * @Date: 2020/10/20 16:55
 * @Version: 1.0
 */
@RestController(value = "/api/users")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/login")
    private Object userLogin() {
        return "user login success";
    }

}
