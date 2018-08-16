package com.demo.springbootdemo.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.demo.springbootdemo.mapper")
public class MybatisConfig {

    @Bean(name = "myBatisDataSource")
    public DataSource myBatisDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/bootdemo?useUnicode=true&characterEncoding=utf-8");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("123456");

        return driverManagerDataSource;
    }
    @Bean(name = "mybatisSqlSessionFactory")
    public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("myBatisDataSource") DataSource dataSource)throws  Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/*.xml"));
        PageInterceptor pageInterceptor = getPageInterceptor();
        bean.setPlugins(new Interceptor[]{pageInterceptor});
        return bean.getObject();
    }
    @Bean(name = "mybatisTransactionManage")
    public DataSourceTransactionManager mybatisTransactionManage(@Qualifier("myBatisDataSource") DataSource dataSource){
        DataSourceTransactionManager mybatisTransactionManage =  new DataSourceTransactionManager();
        mybatisTransactionManage.setDataSource(dataSource);
        return  mybatisTransactionManage;
    }
    @Bean(name = "mybatisSqlSessionTemplate")
    public SqlSessionTemplate mybatisSqlSessionTemplate(@Qualifier("mybatisSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
    public  PageInterceptor getPageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect","mysql");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}