package com.weizhouck.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 自定义Spring 事件
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @since 2020/6/22 0022 0:35
 **/
@Configuration
public class MyCustomEventDemo implements ApplicationContextAware, ApplicationEventPublisherAware, BeanPostProcessor {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        //3
        applicationEventPublisher.publishEvent(new MyEvent("from @PostConstruct -> @Autowired applicationEventPublisher"));
        //4
        applicationContext.publishEvent(new MyEvent("from @PostConstruct -> @Autowired applicationContext"));

    }

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyCustomEventDemo.class);
        context.addApplicationListener(new MyListener());
        context.refresh();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.publishEvent(new MyEvent("from @ApplicationContextAware -> setApplicationContext"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new MyEvent("from @ApplicationEventPublisherAware -> setApplicationEventPublisher"));
    }

}
