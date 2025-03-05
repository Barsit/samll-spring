package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.BeanFactory;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.support
 * @author:db
 * @createTime:2025/2/26 21:04
 * @version:1.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
//    获得了获取 、注册bean的能力
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
//        检查bean是否已存在
        Object bean = getSingleton(name);
        if(bean != null){
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        bean = createBean(name, beanDefinition, args);
        return (T) bean;

//       获取BeanDefinition
//        创建bean
//
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
