package org.barsit.smallspring.beans.context.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.ConfigurableListableBeanFactory;
import org.barsit.smallspring.beans.factory.support.DefaultListableBeanFactory;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.beans.context.support
 * @author:db
 * @createTime:2025/3/8 20:09
 * @version:1.0
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
