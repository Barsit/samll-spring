package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.BeanFactory;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.factory.BeanPostProcessor;
import org.barsit.smallspring.beans.factory.factory.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.factory.support
 * @author:db
 * @createTime:2025/2/26 21:04
 * @version:1.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
//    获得了获取 、注册bean的能力

    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
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
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }


    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    public abstract Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    public abstract Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
