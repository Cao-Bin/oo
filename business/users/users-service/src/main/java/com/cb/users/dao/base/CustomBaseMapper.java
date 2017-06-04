package com.cb.users.dao.base;


import tk.mybatis.mapper.common.*;
import tk.mybatis.mapper.common.special.InsertListMapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;

/**
 * Created by caobin on 2016-09-29 14:38:54.
 */
public interface CustomBaseMapper<T> extends BaseMapper<T>,ConditionMapper<T>,IdsMapper<T>,MySqlMapper<T>,RowBoundsMapper<T>,InsertListMapper<T>,InsertUseGeneratedKeysMapper<T>,Marker {

}
