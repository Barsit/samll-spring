package org.barsit.smallspring.beans.factory.factory;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.ConfigurableListableBeanFactory;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.factory
 * @author:db
 * @createTime:2025/3/8 20:06
 * @version:1.0
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
