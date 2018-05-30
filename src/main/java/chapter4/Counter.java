package chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 使用Java监视器模式的线程安全计数器
 */
@ThreadSafe
public final class Counter {
    @GuardedBy(value = "this")
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {//变量的状态空间，变量value的状态空间为0~Long.MAX_VALUE，即value的取值范围
            throw new IllegalStateException("counter overflow");
        }
        return ++value;
    }
}
