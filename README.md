# https://github.com/dongyan3721/ProjectManagementSPM


### 软件架构
springboot+vue+elementui+mysql



### 安装教程
#### 项目准备工作
- 安装jdk1.8环境 (**不要安装更高的版本**)
- 安装node.js环境 node.js版本15.14.0，<b>必须安装这个版本</b>
- 安装maven环境，3.0以上，用idea捆绑的maven就行
- Github注册账号发我，我拉你们进开发者
- 数据库就用远程121.40.131.167的spm数据库，基本的表已经建好了
- redis远程也是121.40.131.167，这个不用管，后台用到了
-

#### 项目结构

1. ruoyi-admin：后台模块，包含后台管理相关功能,开发的时候主要用这个模块开发，
2. ruoyi-common：公共模块，包含实体类、工具类等,
3. ruoyi-framewordk：核心模块，包含一些核心的配置，不用管
4. ruoyi-generator：代码生成模块，代码基本的增删改查后台和前台都可以生成，基本功能开发的时候可以用这个模块生成基本的代码，然后再在这个基础上开发
5. ruoyi-quartz：定时任务模块，不用管
6. ruoyi-system：系统模块，包含系统管理相关功能，开发的时候很多实体、mapper、service都在这个模块里面
7. ruoyi-ui：前台模块，包含前台展示相关功能，开发的时候主要用这个模块开发，前台的页面放在这个模块的resources/templates下面，前台的静态资源放在resources/static下面

### 项目运行
#### 后台模块
1.  [RuoYiApplication.java]中的main方法运行，端口为applicaiton.yml中配置的端口现在是8080可以不用改，这个和前台模块访问的端口一样，如果要改的话，前台模块的配置文件也要改

#### 前台模块
```
# 进入项目目录
cd ruoyi-ui

# 安装依赖
npm install

# 强烈建议不要用直接使用 cnpm 安装，会有各种诡异的 bug，可以通过重新指定 registry 来解决 npm 安装速度慢的问题。
npm install --registry=https://registry.npmmirror.com

# 本地开发 启动项目
npm run dev
```


### 开发方式
1. 从远程仓库拉取项目
2. 在master分支上开发，开发完成后，push到远程仓库合并项目代码

### 开发流程
- 需求分析，确定需求
- 根据需求，设计数据库表，确定表的字段
- 根据表的字段，生成最基础的实体类，mapper，service，controller，前台页面，并把生成的代码复制到项目对应的目录中
- 也可实际需求，自己修改或添加实体类，mapper，service，controller，前台页面
- 复制到项目对应的目录中后，修改实体类，mapper，service，controller，前台页面的内容，实现具体的功能


### 项目部署
**参考原官方文档**
