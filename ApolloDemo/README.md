Apollo Demo

一、启动项目前需要做以下Apollo服务搭建工作：

1、先下载[apollo-build-scripts](https://github.com/nobodyiam/apollo-build-scripts)
这个东西主要是提供Apollo的服务，包含web页面，用于配置管理等；Apollo服务端，客户端，用户向你的项目提供动态修改生效的服务功能。

2、下载后需要创建数据库（供Apollo使用）：

- a、[ApolloPortalDB](https://github.com/nobodyiam/apollo-build-scripts/blob/master/sql/apolloportaldb.sql)

- b、[ApolloConfigDB](https://github.com/nobodyiam/apollo-build-scripts/blob/master/sql/apolloconfigdb.sql)

然后打开下载的项目，里面有个`demo.sh`的文件，修改其中`apollo_config_db_password`和`apollo_portal_db_password`的信息，如果有其他数据库配置信息不同也需要修改

3、启动Apollo服务
在`demo.sh`目录中，打开cmd，然后输入：`demo.sh start`
等待黑窗口中的启动【默认使用8070(web)，8080，8090端口】

4、启动成功后访问

网址：http://localhost:8070/

默认账密：apollo/admin

5、测试服务是否可用

在web页面上配置一些key:value，然后启动`demo.sh client`

输入key，enter后如果能得到value就成功了


创建用户页面：http://localhost:8070/user-manage.html

二、启动项目

application.yml中的apollo.meta即为链接Apollo服务的地址。

app.id是本项目在我们本地启动的Apollo服务中的唯一标识，需要自己手动创建。

apollo.bootstrap.enabled：在应用启动阶段，向Spring容器注入被托管的application.properties文件的配置信息。

apollo.bootstrap.eagerLoad.enabled：将Apollo配置加载提到初始化日志系统之前。


通过get请求

http://localhost:8123/test1

http://localhost:8123/test2

和在 http://localhost:8070/config.html?#/appid=springboot-test-apollo 修改配置的值来查看效果


注意：普通配置和logLevel之类的配置是两种不同的生效方法，详见com.example.demo.config.LoggerConfig中的注释信息
