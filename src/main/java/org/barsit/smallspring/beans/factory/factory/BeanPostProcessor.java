package org.barsit.smallspring.beans.factory.factory;

import org.barsit.smallspring.beans.BeansException;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.factory
 * @author:db
 * @createTime:2025/3/8 20:07
 * @version:1.0
 */
public interface BeanPostProcessor {
//    执行构造函数之前执行
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;
    //    执行构造函数之前执行
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
