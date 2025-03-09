package org.barsit.smallspring.beans.factory.support;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.core.io.DefaultResourceLoader;
import org.barsit.smallspring.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, DefaultResourceLoader defaultResourceLoader) {
        this.registry = registry;
        this.resourceLoader = defaultResourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public abstract void loadBeanDefinitions(String... location) throws BeansException;
}
