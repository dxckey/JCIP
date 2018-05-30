package chapter2;

public class LoggingWidget extends Widget {
    @Override
    public synchronized void doSomething() {
        System.out.println(toString() + ": call doSomething");
        super.doSomething();
    }
}
