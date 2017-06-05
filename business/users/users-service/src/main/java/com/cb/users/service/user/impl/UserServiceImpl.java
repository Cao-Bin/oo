package com.cb.users.service.user.impl;

import com.cb.users.api.vo.UserVO;
import com.cb.users.dao.mysql.UserDAO;
import com.cb.users.entity.mysql.UserEntity;
import com.cb.users.service.user.UserService;
import com.cb.users.util.DozerMapperUtil;
import com.cb.users.util.JsonUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Created by oo on 17-6-4.
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<UserVO> findUsers(String username, Integer pageNum, Integer pageSize){
        Condition condition = new Condition(UserEntity.class);
        Condition.Criteria criteria = condition.createCriteria();
        if(StringUtils.isNotEmpty(username)) {
            criteria.andLike("username", username);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<UserEntity> userEntities = userDAO.selectByCondition(condition);
        List<UserVO> userVOS = DozerMapperUtil.mapList(userEntities, UserVO.class);
        return userVOS;
    }

    @Cacheable(value = "userCache",keyGenerator = "keyGenerator")
    @Override
    public UserVO findUser(Long id){
        UserEntity userEntity = userDAO.selectByPrimaryKey(id);
        LOGGER.info("ready to cache userEntity {}", JsonUtil.toJson(userEntity));
        return DozerMapperUtil.map(userEntity, UserVO.class);
    }
}
