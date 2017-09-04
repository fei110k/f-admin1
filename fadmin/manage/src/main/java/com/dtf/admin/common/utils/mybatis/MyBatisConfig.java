package com.dtf.admin.common.utils.mybatis;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

/**
 * Mybatis 初始化类
 * @author Administrator
 */
@Configuration
public class MyBatisConfig {
	
	/**
	 * 分页拦截工具
	 * @return
	 */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        //添加配置，也可以指定文件路径
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "false");	//如果设置为true，结果为空，会返回最后一页数据
        pageHelper.setProperties(p);
        return pageHelper;
    }

}