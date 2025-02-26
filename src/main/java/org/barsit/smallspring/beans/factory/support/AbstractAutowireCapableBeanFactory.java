package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.support
 * @author:db
 * @createTime:2025/2/26 21:17
 * @version:1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
//      通过其  beanDefinition 反射
        Object bean = null;
        try {
           bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException |IllegalAccessException  e) {
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingeton(beanName,bean);
        return bean;
    }
}
