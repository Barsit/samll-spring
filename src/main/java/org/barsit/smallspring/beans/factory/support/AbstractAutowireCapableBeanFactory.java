package org.barsit.smallspring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.DisposableBean;
import org.barsit.smallspring.beans.factory.InitializingBean;
import org.barsit.smallspring.beans.factory.PropertyValue;
import org.barsit.smallspring.beans.factory.PropertyValues;
import org.barsit.smallspring.beans.factory.factory.BeanDefinition;
import org.barsit.smallspring.beans.factory.factory.BeanPostProcessor;
import org.barsit.smallspring.beans.factory.factory.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        // 注册实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
//        加入单例
        addSingleton(beanName,bean);
        return bean;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
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
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        }catch (Exception e){
            throw  new BeansException("Invoke init Method of +[" + beanName + "] failed");
        }

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }
    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 1. 实现接口 InitializingBean
        if(wrappedBean instanceof InitializingBean){
            ((InitializingBean) wrappedBean).afterPropertiesSet();
        }
        // 2. 配置信息 init-method {判断是为了避免二次执行销毁}
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method method = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(method == null){
                throw new BeansException("could not find init method named" + initMethodName );
            }
//            反射执行方法
            method.invoke(wrappedBean);
        }
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
