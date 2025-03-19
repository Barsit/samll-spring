package org.barsit.smallspring.test.observerPattern;

public class ObserverImpl1 implements Observer{
    private Subject subject;

    public ObserverImpl1(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("start update " + this);
    }
}
