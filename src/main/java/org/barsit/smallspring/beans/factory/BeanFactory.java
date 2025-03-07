package org.barsit.smallspring.beans.factory;

import org.barsit.smallspring.beans.BeansException;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory
 * @author:db
 * @createTime:2025/2/26 20:56
 * @version:1.0
 */
public interface BeanFactory {
    public Object  getBean(String name) throws BeansException;//f返回值？
    public  Object getBean(String name, Object... args) throws BeansException;
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;


}
