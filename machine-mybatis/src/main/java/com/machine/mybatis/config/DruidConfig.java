package com.machine.mybatis.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @Description: DruidConfig
 *
 * @author machine
 * @date 2017年12月18日 上午12:53:53
*/
@Configuration
public class DruidConfig {

	/**
	 * @Description: druid servle配置
	 *
	 * @author machine
	 * @date 2017年12月18日 上午12:54:45
	*/
	@Bean
	public ServletRegistrationBean fruidServlet() {
		ServletRegistrationBean srBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		srBean.addInitParameter("allow", "127.0.0.1");
		srBean.addInitParameter("deny", "192.168.100.200");
		srBean.addInitParameter("loginUsername", "machine");
		srBean.addInitParameter("loginPassword", "machine");
		srBean.addInitParameter("resetEnable", "false");
		return srBean;
	}

	/**
	 * @Description:  filter
	 *
	 * @author machine
	 * @date 2017年12月18日 上午12:54:06
	*/
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean frBean = new FilterRegistrationBean();
		frBean.setFilter(new WebStatFilter());
		frBean.addUrlPatterns("/*");
		frBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
		return frBean;
	}

	/**
	 * @Description:yml配置 
	 *
	 * @author machine
	 * @date 2017年12月18日 上午12:54:20
	*/
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druidDataSource() {
		return new DruidDataSource();
	}
}