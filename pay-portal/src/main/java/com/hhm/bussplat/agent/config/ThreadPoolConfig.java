package com.hhm.bussplat.agent.config;

import com.hhm.bussplat.util.thread.NameThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池哦配置
 * @author q
 * @time 2020/5/17 9:02 下午
 */
@Configuration
public class ThreadPoolConfig {
    /**
     * 线程池-测试
     * @return
     */
    @Bean( name = "testExecutorService")
    public ExecutorService testExecutorService(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),  new NameThreadFactory("TEST"),
                new ThreadPoolExecutor.DiscardPolicy());
        ThreadPoolManager.register(threadPoolExecutor);
        return threadPoolExecutor;
    }
}
