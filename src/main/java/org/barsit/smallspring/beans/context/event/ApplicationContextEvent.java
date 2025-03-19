package org.barsit.smallspring.beans.context.event;

import org.barsit.smallspring.beans.context.ApplicationContext;

public class ApplicationContextEvent extends  ApplicationEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }
    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
