package org.barsit.smallspring.beans.factory;

public interface BeanNameAware extends Aware {
    void setBeanName(String name);
}
