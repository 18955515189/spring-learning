package com.weizhouck.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener} 注解方式 示例
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @since 2020/6/21 0021 17:39
 **/
@EnableAsync
public class ApplicationListenerAnnotationDemo {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ApplicationListenerAnnotationDemo.class);
        context.register(MyApplicationListener.class);
        //启动应用上下文
        context.refresh();
        /*context.start();
        context.close();*/

    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            System.out.println(" MyApplicationListener - 接收到 Spring 事件: "+contextRefreshedEvent);
        }
    }

    @EventListener
    public void onApplicationEvent(ApplicationEvent applicationEvent){
        println("@EventListener 接收到 Spring 事件: "+applicationEvent);
    }

    @EventListener
    @Async
    @Order(2)
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent){
        println("@EventListener 接收到 ContextRefreshedEvent1 事件: "+applicationEvent);
    }

    @EventListener
    @Async
    @Order(1)
    public void onApplicationEvent2(ContextRefreshedEvent applicationEvent){
        println("@EventListener 接收到 onApplicationEvent2 事件: "+applicationEvent);
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent applicationEvent){
        println("@EventListener 接收到 ContextStartedEvent 事件: "+applicationEvent);
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent applicationEvent){
        println("@EventListener 接收到 ContextClosedEvent 事件: "+applicationEvent);
    }

    private static void println(Object printable){
        System.out.printf(" [线程: %s] : %s\n",Thread.currentThread().getName(),printable);
    }
}
