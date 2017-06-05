package com.cb.users.controller;

import com.cb.users.api.controller.UserController;
import com.cb.users.api.vo.UserVO;
import com.cb.users.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;
    @Override
    public List<UserVO> users(@RequestHeader String TOKEN, @RequestParam(required = false) String username,@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        return userService.findUsers( username, pageNum,  pageSize);
    }

    @Override
    public UserVO user(@RequestHeader String TOKEN, @PathVariable Long id) {
        return userService.findUser(id);
    }
}
