package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.support
 * @author:db
 * @createTime:2025/2/26 21:17
 * @version:1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
//创建bean Instance
        Object bean = null;
        try {
            bean =  createBeanInstance(beanDefinition,beanName,args);
        }catch (Exception e){
            throw new BeansException("Instance of bean failed",e);
        }
//        加入单例
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
//        匹配构造器
        Constructor constructorUse = null;
        Constructor[] constructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            if(constructor.getParameterTypes().length==args.length){
                constructorUse = constructor;
                break;
            }
        }
//        创建bean
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorUse,args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
