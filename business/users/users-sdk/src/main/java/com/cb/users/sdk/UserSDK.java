package com.cb.users.sdk;

import com.cb.users.api.controller.UserController;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient("users")
public interface UserSDK extends UserController {

}
