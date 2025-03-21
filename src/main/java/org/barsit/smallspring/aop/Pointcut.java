package org.barsit.smallspring.aop;

public interface Pointcut {

    ClassFilter getClassFilter();
    MethodMatcher getMethodMatcher();


}
