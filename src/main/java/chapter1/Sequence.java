package chapter1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 线程安全的数值序列生成器
 */
@ThreadSafe
public class Sequence {
    @GuardedBy(value = "this")
    private int value;

    public synchronized int getNext() {
        return value++;
    }
}
