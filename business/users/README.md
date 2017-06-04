# 项目结构

## api:定义服务对外的接口,结构异常等信息

    api:定义swagger接口.
        1. 每个类上的requestmapping url前面添加v*版本号
        2. 类顶部@Api 添加了tags 后,内部的所有方法将进入该tag归类.如果再在方法上加tag,则这个方法同时属于两个tag
        3. @ApiOperation 只填写value即可
        4. @ApiResponses 需要描述200 ,400 ,500的对应返回信息.400需要在message中填写具体的信息.见resources/exception.yml.新增加的exception,需要在yml中记录,不能重复code.
        5. 每个异常内部定义好code,new 新实例只能修改errormsg
        6. 返回结构不再使用ResponseEntity包裹,必填内容,需要加required
        7. 示例
```
        public class Constants {
            public static final String SWAGGER_PREFIX_HTML_RED_OK="<B style='color:red'>[OK]</B>";
        }
        
        @Api(description = "XX服务", tags = "XX分类")
        @RequestMapping(value = "/v1/vehicle-console")
        public interface ConsoleApi {
            @ApiOperation(value = Constants.SWAGGER_PREFIX_HTML_RED_OK+ "xx")
            @ApiResponses({
                    @ApiResponse(code = 200, message = "xx", response = AddressVO.class,responseContainer = "List"),
                    @ApiResponse(code = 400, message =
                                    ExceptionCode.SYS_ERROR_CODE+":"+ExceptionCode.SYS_ERROR_MSG+"</br>"+
                                    ExceptionCode.PARAM_ERROR_CODE+":"+ExceptionCode.PARAM_ERROR_MSG+"</br>"+
                                    ExceptionCode.SERVICE_DISABLE_CODE+":"+ExceptionCode.SERVICE_DISABLE_MSG+"</br>"
                            , response = ErrorInfoVO.class),
                    @ApiResponse(code = 500, message = "系统内部错误", response = ErrorInfoVO.class)
            })
            @RequestMapping(value = "/useraddress", method = RequestMethod.GET)
            List<AddressVO> getCompanyAndHomeAddress(@ApiParam(value = "userId", required = true) Long userId, @ApiParam(required = true)Source source);
        }
```
    constant:定义公共常量
    exception:定义异常
    vo:定义对外的参数结构及返回结构

## micro-service:定义微服务逻辑实现及数据访问

    annotation:注解
    aop:存放切面,拦截器,filter相关类
    config:项目启动相关配置
    constant:常量
    controller:对外接口的实现,仅定义,不实现,内部可再按业务划分.*Controller结尾
    dao:数据访问层,访问数据库,访问第三方数据.*Dao结尾
    entity:业务模型,包含数据库,remote包含三方数据模型.*Entity结尾
    service:业务实现.接口*Service,实现:*ServiceImpl
    util常用工具栏,以*Util结尾


## sdk:对外提供的sdk(如果需要提供SDK)

    直接继承api模块中的接口定义
```
    @FeignClient("users")
    public interface VsmartMgApi /*extends ConsoleApi*/ {

    }
```
