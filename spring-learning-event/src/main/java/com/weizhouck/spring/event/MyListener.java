package com.weizhouck.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @since 2020/6/22 0022 1:06
 **/
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.printf("[MyLinstener 接收到 myEvent]: %s",myEvent.getMessage());
    }
}