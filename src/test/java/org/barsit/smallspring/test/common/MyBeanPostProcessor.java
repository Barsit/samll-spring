package org.barsit.smallspring.test.common;

import org.barsit.smallspring.beans.BeansException;
import org.barsit.smallspring.beans.factory.factory.BeanPostProcessor;
import org.barsit.smallspring.test.bean.UserService;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.test.common
 * @author:db
 * @createTime:2025/3/9 10:26
 * @version:1.0
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)){
            UserService service = (UserService)bean;
            service.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
