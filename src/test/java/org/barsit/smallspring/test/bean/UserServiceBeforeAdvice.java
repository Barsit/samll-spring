package org.barsit.smallspring.test.bean;

import org.barsit.smallspring.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.test.bean
 * @author:db
 * @createTime:2025/3/22 10:53
 * @version:1.0
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }
}
