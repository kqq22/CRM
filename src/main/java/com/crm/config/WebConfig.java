package com.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springmvc总配置
 */
@Configuration//创建一个空的配置文件
@ComponentScan("com.crm.controller")//扫描所有分控制的类
@EnableWebMvc//开启springmvc的所有注解
public class WebConfig extends WebMvcConfigurerAdapter {
	/**
	 * 配置视图解析器
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		//设置前缀
		viewResolver.setPrefix("/");
		//设置后缀
		viewResolver.setSuffix(".jsp");
		//设置bean在请求属性中也可以访问setCacheable
		viewResolver.setCache(false);
		viewResolver.setExposeContextBeansAsAttributes(true);
		return viewResolver;
	}


    /**
     * 开启静态资源访问
     * 重写方法
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
