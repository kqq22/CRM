package com.crm.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 全局配置项
 */
@Configuration
@ComponentScan(
        basePackages = "com.crm",excludeFilters =
@ComponentScan.Filter(
        type= FilterType.ANNOTATION,
        value = {EnableWebMvc.class, Controller.class}//排除  EnableWebMvc，Controller
)
)
@MapperScan("com.crm.mapper")
public class RootConfig {
    /**
     * 数据源配置
     * @return
     */
    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/crm");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setInitialSize(5);
        dataSource.setMaxIdle(10);
        dataSource.setMinIdle(10);
        dataSource.setMaxTotal(5);
        dataSource.setMaxWaitMillis(1000);
        return dataSource;
    }

    /**
     * 获取SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        factoryBean.setDataSource(dataSource);
        //设置分页的拦截器
        PageInterceptor pageInterceptor = new PageInterceptor();
        //创建插件需要的参数集合
        Properties properties = new Properties();
        //配置数据库方言 为mysql
        properties.setProperty("helperDialect", "mysql");
        //配置分页的合理化数据
        properties.setProperty("reasonable", "true");
        pageInterceptor.setProperties(properties);
        //将拦截器设置到sqlSessionFactroy中
        factoryBean.setPlugins(new PageInterceptor[]{ pageInterceptor });
        return factoryBean.getObject();
    }
}
