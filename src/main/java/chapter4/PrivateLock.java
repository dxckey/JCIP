package chapter4;

import chapter2.Widget;
import net.jcip.annotations.GuardedBy;

public class PrivateLock {
    private final Object lock = new Object();
    @GuardedBy(value = "lock")
    Widget widget;

    void doSomething() {
        synchronized (lock) {
            widget.doSomething();
        }
    }
}
