package com.cb.users.controller;

import com.cb.users.api.controller.UserController;
import com.cb.users.api.vo.UserVO;
import com.cb.users.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;
    @Override
    public List<UserVO> users(String TOKEN, String username) {
        return null;
    }
}
