package org.barsit.smallspring.beans.factory;

import org.barsit.smallspring.beans.BeansException;

public interface DisposableBean {
    void  destroy() throws Exception;
}
