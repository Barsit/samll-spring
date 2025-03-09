package org.barsit.smallspring.beans.factory.factory;

import org.barsit.smallspring.beans.factory.HierarchicalBeanFactory;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.factory
 * @author:db
 * @createTime:2025/3/7 11:22
 * @version:1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
