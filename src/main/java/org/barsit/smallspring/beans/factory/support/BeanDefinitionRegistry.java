package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.support
 * @author:db
 * @createTime:2025/2/26 21:18
 * @version:1.0
 */
public interface BeanDefinitionRegistry {
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition);
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    boolean containsBeanDefinition(String beanName);
    String[] getBeanDefinitionNames();
}
