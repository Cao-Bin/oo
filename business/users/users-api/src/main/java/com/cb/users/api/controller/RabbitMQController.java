package com.cb.users.api.controller;

import com.cb.users.api.vo.ErrorResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(tags = "MQ", description = "RabbitMQ")
public interface RabbitMQController {


    @ApiOperation(value = "发送")
    @ApiResponses({
            @ApiResponse(code = 400, message = "业务错误", response = ErrorResponseVO.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = ErrorResponseVO.class)
    })
    @RequestMapping(value = "/v1/rabbitmq/send", method = RequestMethod.POST)
    void send(String routingKey, String message);

}
