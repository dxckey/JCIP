package chapter3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 避免了chapter3.MutableInteger中会发生失效数据的情况
 */
@ThreadSafe
public class SynchronizedInteger {
    @GuardedBy(value = "this")
    private int value;

    public synchronized int get() {
        return value;
    }

    public  synchronized void set(int value) {
        this.value = value;
    }
}
