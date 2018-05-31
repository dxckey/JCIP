package chapter1;

import net.jcip.annotations.NotThreadSafe;

/**
 * 非线程安全的数值序列生成器
 */
@NotThreadSafe
public class UnsafeSequence {
    private int value;

    public int getNext() {
        return value++;
    }
}
