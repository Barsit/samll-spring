package org.barsit.smallspring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.PropertyValue;
import org.barsit.smallspring.beans.factory.PropertyValues;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.factory.BeanPostProcessor;
import org.barsit.smallspring.beans.factory.factory.BeanReference;

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
//            属性填充
            applyPropertyValues(beanName,bean,beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
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
            if(args!=null && constructor.getParameterTypes().length==args.length){
                constructorUse = constructor;
                break;
            }
        }
//        创建bean
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructorUse,args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
//        获取全部属性
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        PropertyValue[] propertyValuesList = propertyValues.getPropertyValuesList();
//        遍历
       try {
           for (PropertyValue propertyValue : propertyValuesList) {
               String name = propertyValue.getName();
               Object value = propertyValue.getValue();
               //        判断依赖
               if(value instanceof BeanReference){
                   BeanReference beanReference = (BeanReference) value;
                   value =getBean(beanReference.getBeanName());
               }
               //         填充属性
               BeanUtil.setFieldValue(bean,name,value);
           }
       }catch (Exception e){
           throw new BeansException("Error setting property values：" + beanName);
       }



    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }
    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

    }
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }
}
