package org.barsit.smallspring.test.event;

import org.barsit.smallspring.beans.context.event.ApplicationListener;
import org.barsit.smallspring.beans.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
