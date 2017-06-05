package com.cb.users.api.controller;

import com.cb.users.api.vo.ErrorResponseVO;
import com.cb.users.api.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(tags = "用户", description = "用户CRUD")
public interface UserController {

    @ApiOperation(value = "用户列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "业务错误", response = ErrorResponseVO.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = ErrorResponseVO.class)
    })
    @RequestMapping(value = "/v1/users", method = RequestMethod.GET)
    List<UserVO> users( /*@ApiParam(required = true) */ String TOKEN,
                        /*@ApiParam(required = false)*/ String username,
                        /*@ApiParam(required = true) */ Integer pageNum,
                        /*@ApiParam(required = true) */ Integer pageSize
    );

    @ApiOperation(value = "查找指定用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 400, message = "业务错误", response = ErrorResponseVO.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = ErrorResponseVO.class)
    })
    @RequestMapping(value = "/v1/{id}", method = RequestMethod.GET)
    UserVO user(String TOKEN, Long id);
}
