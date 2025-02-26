package org.barsit.smallspring;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.samllspring
 * @author:db
 * @createTime:2025/2/26 20:02
 * @version:1.0
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean(){
        return  this.bean;
    }
}
