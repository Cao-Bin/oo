package com.cb.users.service.json;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JsonService {
    Object getStringFromJSONObject(String json, String key);

    JSONObject getJSONFromString(String jsonString);

    <T> T toBean(String jsonStr, Class<T> beanClass);

    String toJson(Object obj);

    String toJson(Map<String, Object> map);

    String prettyFormatJson(String jsonString);

    Map<String, Object> toMap(String jsonString);

    <T> List<T> toBeanList(String json, Class<T> targetClass);

    <T> Set<T> toBeanSet(String json, Class<T> targetClass);
}
