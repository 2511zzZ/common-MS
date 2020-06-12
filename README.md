# common-MS
一个前后端分离模式下的后台管理系统通用框架

common-MS目前已经完成了日志处理、异常处理、参数校验、结果封装等后台管理系统的基础功能架构，并且提供接口可视化调试功能和简单的CRUD示例。

熟悉本项目后可以将example包已经所有命名中带有Example的文件全部删除。

项目开发历程见：[SpringBoot后端系统系统的基础架构](https://www.cnblogs.com/2511zzZ/p/13109151.html)

### 日志处理

本项目使用Spring Boot默认的Slf4j作为日志框架，能够将日志按级别和日期分类并写入到指定目录的文件下，并且提供了基于AOP的用户请求日志记录。使用示例：

```Java
@Slf4j
// ...
log.info("lombok info test")
```

使用用户请求日志时，需要修改aop.LogAspect中的execution表达式：

```Java
@Pointcut("execution(* priv.zzz.controller..*.*(..))")
```

使用时，可以在yml文件中修改日志输出级别和输出路径。

### 结果封装

本项目对请求返回结果统一做了RESTful风格的封装，主要包括Result和ResultSet两个类，分别用于对单个对象的封装各一组对象的封装，返回结果示例：

```javascript
{
  "timestamp": "2020-06-12T15:44:02.106+08:00",
  "status": 200,
  "message": "success",
  "data": 123,
  "path": "/result"
}
```

使用示例：

```Java
return Result.success(userService.getUserByName(username));
```

### 参数校验

本项目主要使用Hibernate Validator进行参数校验，在model.ExampleUser中可以看到其在实体类上的用例。也可以在Controller传参处使用注解进行校验。

当校验不通过时，系统通过全局的异常处理模块捕获异常，并返回风格一致的错误信息：

```Java
{
  "timestamp": "2020-06-12T15:45:07.874+08:00",
  "status": 400,
  "message": "email邮箱格式错误",
  "data": null,
  "path": "/user"
}
```

### 异常处理

common-MS中主要使用@ControllerAdvice和@ExceptionHandler进行统一异常处理。用户可以在controller.ExceptionController中对不同的异常进行处理，返回信息同样使用Result进行封装。

用户还可以在exception包中自定义异常，并在ExceptionController中进行相应的处理。

```javascript
{
  "timestamp": "2020-06-12T17:49:39.458+08:00",
  "status": -1,
  "message": "这是一个测试异常",
  "data": null,
  "path": "/exception"
}
```

### CRUD

本项目使用MyBatis作为持久层框架并提供了简单的示例：对User实体的增删改查。程序调用过程为经典的Controller→Service(ServiceImpl)→Dao→Mapper.xml形式。用户编写自己的业务逻辑时，修改yml文件中的DataSource相关配置即可开始使用。

### 其他

本系统还集成了一些其他开发辅助功能：

**Druid SQL监控**

系统启动后，访问 /druid/stat 即可进入Druid的SQL监控页，在这里可以看到系统启动后所有SQL的执行情况。

**Swagger2 接口调试可视化**

使用Swagger2之前，要将aop.LogAspect中的`BASE_PACKAGE`修改为要扫描的controller包路径。

系统启动后，访问 /api 或 /doc.html 即可进入接口调试页，通过该页面用户可以不借助PostMan等工具自主调试接口。

### TODO

计划集成Shiro、分页功能、Redis等
