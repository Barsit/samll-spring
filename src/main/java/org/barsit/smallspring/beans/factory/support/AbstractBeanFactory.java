package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.factory.BeanFactory;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.factory.DefaultSingletonBeanRegistry;

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
    public Object getBean(String name) {
        Object bean = getSingleton(name);
        if(bean!=null){//单例
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition);
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName);
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition);
}
