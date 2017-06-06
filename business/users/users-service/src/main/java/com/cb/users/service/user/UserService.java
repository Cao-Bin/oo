package com.cb.users.service.user;

import com.cb.users.api.vo.UserVO;

import java.util.List;

/**
 * Created by oo on 17-6-4.
 */
public interface UserService {
    List<UserVO> findUsers(String username, Integer pageNum, Integer pageSize);

    UserVO findUser(Long id);

    UserVO insert(UserVO userVO);

    UserVO update(UserVO userVO);

    void delete(Long id);
}
