package com.cb.users.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    private ApiInfo apiInfo() {
        Contact contact = new Contact("Cao Bin", "", "346322003@qq.com");
        return new ApiInfo("users API", "users api doc", "1.0",
                "", contact, "yunfeng demo test", "www.beecloud.com");
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cb.users.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(
                    Collections.singletonList(securityContext())
                 )
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());

    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(
                        PathSelectors.any()
                )
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = {new AuthorizationScope("global", "accessEverything")};
        return Lists.newArrayList(new SecurityReference("vehicleApiKey", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("vehicleApiKey", "api_key", ApiKeyVehicle.HEADER.getValue());
    }

    @Bean
    SecurityConfiguration securityConfiguration() {
        return  new SecurityConfiguration("vehicleMicro", "test-screte", "realm",
                "testApp", "apiKey", ApiKeyVehicle.HEADER, "api_key", ",");
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                "validatorUrl",// url
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true);        // showRequestHeaders    => true | false
    }
}
