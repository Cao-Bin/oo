package com.cb.users.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Configuration
@ConditionalOnClass(FastJsonHttpMessageConverter4.class)
public class FastJsonHttpMessageConvertConfiguration {

    @Bean
    FastJsonConfig fastJsonConfig() {
        FastJsonConfig jsonConfig = new FastJsonConfig();
        ParserConfig.getGlobalInstance().addAccept("com.cb.");//解决redis @Type问题
        jsonConfig.setCharset(Charsets.UTF_8);
        jsonConfig.setSerializerFeatures(SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.QuoteFieldNames, SerializerFeature.SortField);
        return  jsonConfig;
    }

    @Bean
    @Autowired
    FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter4(FastJsonConfig jsonConfig) {
        FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter4 = new FastJsonHttpMessageConverter4();
        fastJsonHttpMessageConverter4.setFastJsonConfig(jsonConfig);
        fastJsonHttpMessageConverter4.setSupportedMediaTypes(Lists.newArrayList(APPLICATION_JSON_UTF8));
        return fastJsonHttpMessageConverter4;
    }

    @Bean
    @Autowired
    HttpMessageConverters httpMessageConverters(FastJsonHttpMessageConverter4 fastJsonConverter4) {
        return new HttpMessageConverters(fastJsonConverter4);
    }
}
