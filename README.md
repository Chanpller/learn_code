# learn_code

#### 介绍
学习的源码代码仓库

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)


## maven版本需要

JDK版本	Maven版本支持情况
JDK 1.3	Maven 2.0.11及以下
JDK 1.4	Maven 2.0.11及以下
JDK 1.5	Maven 2.0.11及以上
JDK 1.6	Maven 2.0.11及以上
JDK 1.7	Maven 3.0及以上
JDK 1.8	Maven 3.3及以上
JDK 9	Maven 3.5.0及以上
JDK 10	Maven 3.5.3及以上
JDK 11	Maven 3.5.4及以上
JDK 12	Maven 3.6.0及以上
JDK 13	Maven 3.6.1及以上
JDK 14	Maven 3.6.2及以上
JDK 15	Maven 3.6.3及以上
JDK 16	Maven 3.8.1及以上
JDK 17	Maven 3.8.3及以上

IDEA 报错 Package 'java.util' is declared in module 'java.base', which is not in the module graph
在 IDEA 中更改了 JDK 版本之后报错：Package 'java.util' is declared in module 'java.base', which is not in the module graph，项目中的所有 java.util 和 java.lang 包的代码全部带红色波浪线...

解决方法：清理 IDEA 缓存：
File -> Invalidate caches -> select "Clear file system cache and Local history" -> Invalidate and Restart


springboot 3.0.5 报Cannot load driver class: org.h2.Driver
实际是druid未加载。在resources下创建META-INFO/spring目录。创建文件org.springframework.boot.autoconfigure.AutoConfiguration.imports
文件内容com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure

mybatisplus自定义分页时
入参第一个需要放IPage,Mapper参数返回时需要指定返回参数为Page，否则会报异常
Page<Headline> selectPageMap(IPage<Headline> page, @Param("portalVo") PortalVo portalVo);
org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 4
