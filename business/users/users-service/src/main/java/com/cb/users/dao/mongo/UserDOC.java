package com.cb.users.dao.mongo;

import com.cb.users.entity.mongo.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by oo on 17-6-4.
 */
public interface UserDOC extends MongoRepository<UserEntity,Long>{
}
