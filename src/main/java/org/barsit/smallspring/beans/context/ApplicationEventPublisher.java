package org.barsit.smallspring.beans.context;

import org.barsit.smallspring.beans.context.event.ApplicationEvent;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
