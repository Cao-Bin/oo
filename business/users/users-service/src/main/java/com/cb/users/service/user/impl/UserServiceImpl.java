package com.cb.users.service.user.impl;

import com.cb.users.api.vo.UserVO;
import com.cb.users.dao.mysql.UserDAO;
import com.cb.users.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by oo on 17-6-4.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public List<UserVO> findAll(){
//        return userDAO.selectAll();
        return null;
    }
}
