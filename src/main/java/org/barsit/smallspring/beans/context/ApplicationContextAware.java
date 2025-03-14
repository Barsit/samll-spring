package org.barsit.smallspring.beans.context;

import org.barsit.smallspring.beans.BeansException;

public interface ApplicationContextAware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
