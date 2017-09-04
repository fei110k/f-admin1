# f-admin
使用maven构建的，以spring mvc + mybatis 为核心搭建的后台管理系统基础功能，可直接引入后，在项目中添加目录方式添加业务代码


项目环境
eclipse 4.5.2 （本人使用的是eclipse Mars版本，应该）
maven 3.3.9	
mysql 5.6
jdk 1.7
tomcat 7.0

tomcat的配置
1.加入tomcat后，将路径设置为/
2.在tomcat的VM arguments添加设置：-DCONFIG_PATH=classpath:config
