package org.barsit.smallspring.test.event;

import org.barsit.smallspring.beans.context.event.ApplicationListener;
import org.barsit.smallspring.beans.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
