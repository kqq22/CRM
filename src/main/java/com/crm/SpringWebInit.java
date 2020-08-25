package com.crm;

import com.crm.config.RootConfig;
import com.crm.config.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 初始化DispatcherServlet
 */
public class SpringWebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 全局配置项
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 配置springMVC
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 配置servlet-mapping
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 设置全栈乱码问题
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true);
        return new Filter[] {encodingFilter};
    }
}
