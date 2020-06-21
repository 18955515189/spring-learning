package com.weizhouck.spring.event;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

/**
 * {@link ApplicationListener } 接口方式 示例
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @since 2020/6/21 0021 17:23
 **/
public class ApplicationListenerInterfaceDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        //向应用上下文注册事件
        //方法一:基于 ApplicationListener 注册为 Spring Bean
        context.register(ApplicationListenerInterfaceDemo.class);
        context.register(MyApplicationListener.class);


        //方法二: 基于ConfigurableApplicationContext API 实现
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                println(" 接收到 Spring 事件: "+applicationEvent);
            }
        });

        //启动应用上下文
        context.refresh();
        //启动 Spring上下文
        //context.start();
        //关闭应用上下文
        //context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("helloworld123456") {
        });
        applicationEventPublisher.publishEvent("helloworld8888888");
    }

    class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            println(" MyApplicationListener - 接收到 Spring 事件: "+contextRefreshedEvent);
        }
    }

    private static void println(Object printable){
        System.out.printf(" [线程: %s] : %s\n",Thread.currentThread().getName(),printable);
    }



}
