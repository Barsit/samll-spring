package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {

//    通过反射创建bean对象
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
//        获取bean信息
        Class beanClass = beanDefinition.getBeanClass();
//        判空
        try {
            if (ctor != null) {
//                有参构造
                return beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance();
            } else {
//                无参构造
                return beanClass.getDeclaredConstructor().newInstance();
            }
            } catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
                throw new BeansException("Failed to instantiate [" + beanName +"]",e);
            }
        }
    }