一个后端 demo ，集成了常用的后端框架/工具和公司的环境。

使用时将整个项目拷贝下来，删除 Application 相关代码，修改包名和相关路径即可。

默认使用 `application.yaml` 和 `application-dev.yaml`，修改 `application-dev.yaml` 内相关配置后可直接本地运行。

# 目录

```
+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---fhzn
|   |   |           \---demo
|   |   |               +---config # AutoConfig 相关
|   |   |               +---constant # 常量相关
|   |   |               +---entity # 领域和实体相关
|   |   |               +---mapper # mybatis 操作相关
|   |   |               +---remote # 远程调用相关
|   |   |               |   \---wups
|   |   |               |       \---vo
|   |   |               +---service # service
|   |   |               \---web
|   |   |                   +---controller
|   |   |                   +---converter # 对请求/返回参数进行转换
|   |   |                   +---interceptor # 拦截器
|   |   |                   +---request # 入参
|   |   |                   \---vo # 出参
|   |   \---resources
|   |       \---db
|   |           \---migration 数据库表变更记录
```

# 框架

* Spring-Boot 2.7.9
  
  相关使用见文档 https://docs.spring.io/spring-boot/docs/2.7.9/reference/htmlsingle/
* MyBatis-Plus
  
  基于 MyBatis 的增强工具，可简化和提高开发效率，见文档 https://baomidou.com/ 。相关说明：
    * 记得修改扫描范围。[MybatisPlusConfig.java](src%2Fmain%2Fjava%2Fcom%2Ffhzn%2Fdemo%2Fconfig%2FMybatisPlusConfig.java)
    * 逻辑删除。数据库默认字段 `if_deleted`
    * 开启了自动分页。https://baomidou.com/pages/97710a/#paginationinnerinterceptor
    * ...
* Swagger

  集成 [SpringDoc](https://springdoc.org/) ，和 SpringFox 这种无本质差别。

  项目启动后 swagger-ui 地址：http://${host}:${port}/swagger-ui.html

  开启/关闭 swagger 见环境变量 `swagger.enabled` 

* Spring-Cloud-OpenFeign

  一个基于接口+注解方式进行 http 调用的框架，相关文档见 https://docs.spring.io/spring-cloud-openfeign/docs/3.1.6/reference/html/

* lombok

  一个在编译期进行代码生成的工具，相关 Case 见文档 https://projectlombok.org/features/

* guava

  google 提供的 Java 工具类

* Flyway

  借助该工具，实现数据库表上线自动化，无需运维/开发介入，相关文档 https://flywaydb.org/documentation/

# 公司内部

* common-webapi

  相关说明见 https://gitlab.fhzny.com:22222/base/common-webapi

* CI

  基于 Gitlab CI/CD ，推代码或打完 tag 后触发打 Docker 镜像的流程。 gitlab ci 的相关文档：https://docs.gitlab.com/ee/ci/yaml/

  使用时需修改 `.gitlab-ci.yml` 内变量 `IMAGE_PROJECT` 和 `IMAGE_NAME` 。
        
  | variable      | description |
  |-------------|-------------|
  | IMAGE_PROJECT | harbor 仓库 |
  | IMAGE_NAME    | docker 镜像名称 |

分页请求
请求 URL:
http://localhost:5173/api/auth-service/user/query?pageSize=10&page=1
请求方法:
GET
状态代码:
404 Not Found
远程地址:
[::1]:5173
引用站点策略:
strict-origin-when-cross-origin

条件查询
请求 URL:
http://localhost:5173/api/auth-service/user/query?pageSize=10&page=1&nickname=111&status=1
请求方法:
GET
状态代码:
404 Not Found
远程地址:
[::1]:5173
引用站点策略:
strict-origin-when-cross-origin