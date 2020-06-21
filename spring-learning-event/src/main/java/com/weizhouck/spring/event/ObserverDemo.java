package com.weizhouck.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link java.util.Observer} 示例
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @since 2020/6/20 0020 12:10
 **/
public class ObserverDemo {

    public static void main(String[] args){
        EventObservable observable = new EventObservable();
        //添加观察者（监听者）
        observable.addObserver(new EventObserver());
        //发布消息
        observable.notifyObservers("Hello,World");
    }

    static class EventObservable extends Observable{
        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }
        @Override
        public void notifyObservers(Object org) {
            setChanged();
            super.notifyObservers(new EventObject(org));
            clearChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {
        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println(" 收到事件: "+eventObject);
        }
    }

}
