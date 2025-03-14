package org.barsit.smallspring.beans.context.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.context.ApplicationContext;
import org.barsit.smallspring.beans.context.ConfigurableApplicationContext;
import org.barsit.smallspring.beans.factory.ConfigurableListableBeanFactory;
import org.barsit.smallspring.beans.factory.factory.BeanFactoryPostProcessor;
import org.barsit.smallspring.beans.factory.factory.BeanPostProcessor;
import org.barsit.smallspring.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.context.support
 * @author:db
 * @createTime:2025/3/8 20:09
 * @version:1.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5. 提前实例化单例Bean对象   单例bean对象容器提前实例化 <-------------> 延迟实例化
        beanFactory.preInstantiateSingletons();
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory() throws BeansException;

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }
    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public void registerShutdownHook() {
//        注册钩子方法
//       this::close 语法糖 ：方法引用
//        new  Thread(System.out::println);
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));

    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
