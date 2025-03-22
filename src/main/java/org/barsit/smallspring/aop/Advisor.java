package org.barsit.smallspring.aop;

import org.aopalliance.aop.Advice;

/**
 * @description:
 * @projectName:samll-spring
 * @see:org.barsit.smallspring.aop
 * @author:db
 * @createTime:2025/3/22 10:17
 * @version:1.0
 */
public interface Advisor {
    Advice getAdvice();
}
