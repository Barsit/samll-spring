package org.barsit.smallspring.test.observerPattern;

import java.util.LinkedHashSet;
import java.util.Set;

public class SubjectImpl2 implements Subject{
    private int state;
    private Set<Observer> observers = new LinkedHashSet<>();
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer o: observers) {
            o.update();
        }

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }
}
