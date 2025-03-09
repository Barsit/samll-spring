package org.barsit.smallspring.beans.factory;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.factory.AutowireCapableBeanFactory;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.factory.ConfigurableBeanFactory;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory
 * @author:db
 * @createTime:2025/3/7 11:17
 * @version:1.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    void preInstantiateSingletons() throws BeansException;
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
