package com.cb.users.api.controller;

import com.cb.users.api.vo.ErrorResponseVO;
import com.cb.users.api.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(tags = "用户", description = "用户")
public interface UserController {

    @ApiOperation(value = "用户列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = Void.class),
            @ApiResponse(code = 400, message = "业务错误", response = ErrorResponseVO.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = ErrorResponseVO.class)
    })
    @RequestMapping(value = "/v1/users", method = RequestMethod.POST)
    List<UserVO> users(@ApiParam(value = "TOKEN", required = true) String TOKEN, @ApiParam(value = "username", required = true) String username);


}
