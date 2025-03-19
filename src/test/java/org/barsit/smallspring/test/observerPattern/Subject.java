package org.barsit.smallspring.test.observerPattern;

public interface Subject {
    public void  attach(Observer observer);
    public void  notifyAllObservers();
}
