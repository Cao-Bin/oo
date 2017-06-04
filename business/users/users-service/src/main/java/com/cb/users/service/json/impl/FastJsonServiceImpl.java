package com.cb.users.service.json.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.cb.users.service.json.JsonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;



@Service
public final class FastJsonServiceImpl implements JsonService {

    @Autowired
    private FastJsonConfig jsonConfig;

    private FastJsonServiceImpl() {
    }

    /**
     * 从json获取指定key的字符串
     *
     * @param json json字符串
     * @param key  字符串的key
     * @return 指定key的值
     */
    @Override
    public  Object getStringFromJSONObject(final String json, final String key) {
        Objects.requireNonNull(json, "json is null");
        return JSON.parseObject(json).getString(key);
    }

    /**
     * 将字符串转换成JSON object
     *
     * @param jsonString json字符串
     * @return 转换成的json对象
     */
    @Override
    public  JSONObject getJSONFromString(final String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return new JSONObject();
        }
        return JSON.parseObject(jsonString);
    }

    /**
     * 将json字符串，转换成指定java bean
     *
     * @param jsonStr   json串对象
     * @param beanClass 指定的bean
     * @param <T>       任意bean的类型
     * @return 转换后的java bean对象
     */
    @Override
    public  <T> T toBean(String jsonStr, Class<T> beanClass) {
        Objects.requireNonNull(jsonStr, "jsonStr is null");
        JSONObject jo = JSON.parseObject(jsonStr);
        jo.put(JSON.DEFAULT_TYPE_KEY, beanClass.getName());
        return JSON.parseObject(jo.toJSONString(), beanClass);
    }

    /**
     * @param <T> 入参对象类型泛型
     * @param obj 需要转换的java bean
     * @return 对应的json字符串
     */
    @Override
    public String toJson(Object obj) {
        Objects.requireNonNull(obj, "obj is null");
        return JSON.toJSONString(obj, defaultSerializerFeature());
    }

    /**
     * 通过Map生成一个json字符串
     *
     * @param map 需要转换的map
     * @return json串
     */
    @Override
    public  String toJson(Map<String, Object> map) {
        Objects.requireNonNull(map, "map is null");
        return JSON.toJSONString(map, defaultSerializerFeature());
    }

    /**
     * 美化传入的json,使得该json字符串容易查看
     *
     * @param jsonString 需要处理的json串
     * @return 美化后的json串
     */
    @Override
    public  String prettyFormatJson(String jsonString) {
        Objects.requireNonNull(jsonString, "jsonString is null");
        return JSON.toJSONString(getJSONFromString(jsonString), true);
    }

    /**
     * 将传入的json字符串转换成Map
     *
     * @param jsonString 需要处理的json串
     * @return 对应的map
     */
    @Override
    public  Map<String, Object> toMap(String jsonString) {
        Objects.requireNonNull(jsonString, "jsonString is null");
        return getJSONFromString(jsonString);
    }

    /**
     * json字符串转列表
     * @param json
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public  <T> List<T> toBeanList(String json, Class<T> targetClass) {
        if(StringUtils.isBlank(json)){
            return null;
        }
        return JSON.parseArray(json,targetClass);
    }

    /**
     * json字符串转集合set
     * @param json
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public  <T> Set<T> toBeanSet(String json, Class<T> targetClass){
        if(StringUtils.isBlank(json)){
            return null;
        }
        Set<T> set = new HashSet<T>();
        set.addAll(JSON.parseArray(json,targetClass));
        return set;
    }

    private  SerializerFeature[] defaultSerializerFeature() {
        return jsonConfig.getSerializerFeatures();
    }
}
