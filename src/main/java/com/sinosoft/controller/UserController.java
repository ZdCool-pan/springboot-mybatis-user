package com.sinosoft.controller;

import com.sinosoft.pojo.Co;
import com.sinosoft.pojo.User;
import com.sinosoft.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/getUser")
    public User getUser(String userid) {
        User user = userService.selectByPrimaryKey(userid);
        return user;
    }
}
