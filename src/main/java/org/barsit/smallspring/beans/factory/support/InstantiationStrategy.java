package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
//    实例化bean对象 BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args);

}
