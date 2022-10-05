## Blog Demo 
> [Holanswide](https://github.com/HolanSwide)

# 功能介绍

# 项目说明

## 后端部分

### 1. 数据库

- [applications.yml](./src/main/resources/application.yml) 中修改数据源配置
- 端口：80[dev]
- 数据库初始化文件 [blog.sql](./file/blog.sql)
- 表：
  - t_user (uid,username,password,auth)
  - t_user_info (uid,email,phone,birth,address,sex,sign)

### 2. 后端接口
- 采用RESTful风格
- dev环境下部署项目后访问swagger查看接口


# 技术栈

## 后端

- Springboot
- Druid
- MyBatis
- SpringSecurity
- Swagger
- SpringMail

## 前端

- Vue
- bootstrap
- axios
- nodejs

# 记录

- @RequestParam 仅仅支持 GET 方式，详情参考[click here](https://blog.csdn.net/m0_49161353/article/details/124156909)
- mysql创建中文全文索引(**注意：本项目使用的版本不支持中文全文索引，需升级至5.6.以上**) [click here](https://blog.csdn.net/qq_27517377/article/details/98801959)

