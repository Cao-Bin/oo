package com.cb.users.service.user.impl;

import com.cb.users.api.vo.UserVO;
import com.cb.users.dao.mysql.UserDAO;
import com.cb.users.entity.mysql.UserEntity;
import com.cb.users.service.user.UserService;
import com.cb.users.util.DozerMapperUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

    /**
     * key:com.cb.users.service.user.impl.UserServiceImpl.findUser.1
     * keyGenerator = "keyGenerator" 等价于 key = "#root.targetClass.name+'.'+#root.methodName+'.'+#id"
     * @param id
     * @return
     */
    @Cacheable(value = "users",key = "'user.'+#id")
    @Override
    public UserVO findUser(Long id){
        UserEntity userEntity = userDAO.selectByPrimaryKey(id);
        return DozerMapperUtil.map(userEntity, UserVO.class);
    }


    @CachePut(value = "users",key = "'user.'+#result.id")
    @Override
    public UserVO insert(UserVO userVO){
        UserEntity userEntity = DozerMapperUtil.map(userVO, UserEntity.class);
        userDAO.insertSelective(userEntity);
        return DozerMapperUtil.map(userEntity, UserVO.class);
    }

    @CachePut(value = "users",key = "'user.'+#result.id")
    @Override
    public UserVO update(UserVO userVO){
        UserEntity userEntity = DozerMapperUtil.map(userVO, UserEntity.class);
        userDAO.updateByPrimaryKeySelective(userEntity);
        return DozerMapperUtil.map(userEntity, UserVO.class);
    }


    @CacheEvict(value = "users",key = "'user.'+#id")
    @Override
    public void delete(Long id){
        userDAO.deleteByPrimaryKey(id);
    }


}
