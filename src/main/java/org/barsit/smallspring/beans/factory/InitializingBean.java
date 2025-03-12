package org.barsit.smallspring.beans.factory;

import org.barsit.smallspring.beans.BeansException;

public interface InitializingBean {
    void afterPropertiesSet() throws BeansException;
}
