package com.cb.users.controller;

import com.cb.users.api.controller.UserController;
import com.cb.users.api.vo.UserVO;
import com.cb.users.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Override
    public UserVO insert(@RequestHeader String TOKEN, @RequestBody UserVO userVO) {
        return userService.insert(userVO);
    }

    @Override
    public UserVO update(@RequestHeader String TOKEN, @RequestBody UserVO userVO, @PathVariable Long id) {
        return userService.update(userVO);
    }

    @Override
    public void delete(@RequestHeader String TOKEN, @PathVariable Long id) {
        userService.delete(id);
    }
}
