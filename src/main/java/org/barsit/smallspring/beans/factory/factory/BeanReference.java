package org.barsit.smallspring.beans.factory.factory;

public class BeanReference {
    private final  String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
