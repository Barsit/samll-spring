package org.barsit.smallspring.aop;

import java.lang.reflect.Method;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.aop
 * @author:db
 * @createTime:2025/3/22 10:17
 * @version:1.0
 */
public interface MethodBeforeAdvice extends BeforeAdvice{
    void before(Method method, Object[] args, Object target) throws Throwable;
}
