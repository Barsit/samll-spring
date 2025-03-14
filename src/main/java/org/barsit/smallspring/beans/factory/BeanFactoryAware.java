package org.barsit.smallspring.beans.factory;

import org.barsit.smallspring.beans.BeansException;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
