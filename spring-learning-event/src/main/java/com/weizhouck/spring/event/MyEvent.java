package com.weizhouck.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @since 2020/6/22 0022 1:05
 **/
public class MyEvent extends ApplicationEvent {
    public MyEvent(String message) {
        super(message);
    }
    @Override
    public String getSource() {return (String)super.getSource();}
    public String getMessage(){return getSource();}
}
