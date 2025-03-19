package org.barsit.smallspring.test.observerPattern;

public class ObserverPatterDemo
{
    public static void main(String[] args) {
        SubjectImpl subject = new SubjectImpl();
        SubjectImpl2 subjectImpl2 = new SubjectImpl2();
        ObserverImpl1 observerImpl1 = new ObserverImpl1(subjectImpl2);
        ObserverImpl1 observerImpl2 = new ObserverImpl1(subjectImpl2);
        subject.setState(10);
        subjectImpl2.setState(10);
    }
}
