package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.BeanFactory;
import org.barsit.smallspring.beans.factory.FactoryBean;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.factory.BeanPostProcessor;
import org.barsit.smallspring.beans.factory.factory.ConfigurableBeanFactory;
import org.barsit.smallspring.utils.ClassUtils;

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
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {
//    获得了获取 、注册bean的能力
    /** ClassLoader to resolve bean class names with, if necessary */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();
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
        Object sharedBean = getSingleton(name);
        if(sharedBean != null){
            return (T) getObjectForBeanInstance(sharedBean,name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
       Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean,name);

//       获取BeanDefinition
//        创建bean
//
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }
    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName);
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    public abstract Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    public abstract Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
