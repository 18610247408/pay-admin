package com.hhm.bussplat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author q
 * @time 2020/5/18 9:19 上午
 */
@MapperScan("com.hhm.bussplat.mapper")
@SpringBootApplication
@EnableTransactionManagement
//@EnableAspectJAutoProxy(exposeProxy=true)
public class AgentApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 设置启动类,用于独立tomcat运行的入口
        return builder.sources(AgentApplication.class);
    }
}
