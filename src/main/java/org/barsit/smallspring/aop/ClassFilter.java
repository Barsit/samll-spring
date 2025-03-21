package org.barsit.smallspring.aop;

public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
