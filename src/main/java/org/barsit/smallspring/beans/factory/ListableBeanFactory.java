package org.barsit.smallspring.beans.factory;

import org.barsit.smallspring.beans.BeansException;

import java.util.Map;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory
 * @author:db
 * @createTime:2025/3/7 11:18
 * @version:1.0
 */
public interface ListableBeanFactory extends BeanFactory{
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
    String[] getBeanDefinitionNames();

}
