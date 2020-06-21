package com.weizhouck.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 层次性 Spring 事件传播示例
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @since 2020/6/22 0022 0:13
 **/
public class HierachialSpringEventDemo {
    public static void main(String[] args){
        //创建 Parent Spring 应用上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyListener.class);
        //创建 current Spring 应用上下文
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.register(MyListener.class);
        //current -> parent
        currentContext.setParent(parentContext);

        //启动应用上下文
        parentContext.refresh();
        currentContext.refresh();
    }

    static class MyListener implements ApplicationListener<ContextRefreshedEvent>{
        private static Set<ContextRefreshedEvent> processedEvents = new LinkedHashSet<>();
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            if(processedEvents.add(event)){
                System.out.printf(" 监听到 Spring 应用上下文[ ID: %s] 的 ContextRefreshedEvent",event.getApplicationContext().getId());
            }
        }
    }
}
