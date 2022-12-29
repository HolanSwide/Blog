## Blog Demo 
> [Holanswide](https://github.com/HolanSwide)

## 项目说明

1. 执行 [blog.sql](./file/blog.sql)初始化数据库
2. 修改数据源配置（数据库账号密码等）[applications.yml](./src/main/resources/application.yml)
3. 运行 BlogApplication 类
4. 登录：{username:holanswide, password:12345}
5. 暂未完成功能：发表文章

## 后端部分

### 1. 数据库

- [applications.yml](./src/main/resources/application.yml) 中修改数据源配置
- 端口：80[dev]
- 数据库初始化文件 [blog.sql](./file/blog.sql)


### 2. 后端接口
- 采用RESTful风格
- dev环境下部署项目后访问swagger查看接口


## 技术栈

### 后端

- Springboot
- Druid
- MyBatis
- SpringSecurity
- Swagger
- SpringMail

### 前端

- Vue
- bootstrap
- axios

## 记录

- @RequestParam 仅仅支持 GET 方式，详情参考[click here](https://blog.csdn.net/m0_49161353/article/details/124156909)
- mysql创建中文全文索引(**注意：本项目使用的版本不支持中文全文索引，需升级至5.6.以上**) [click here](https://blog.csdn.net/qq_27517377/article/details/98801959)

