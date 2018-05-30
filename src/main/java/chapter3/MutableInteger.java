package chapter3;

import net.jcip.annotations.NotThreadSafe;

/**
 * 该类在多线程不同步的情况下容易出现失效数据
 * 线程A调用set，同时线程B调用get。线程B可能会看到更新后的值，也可能看不到
 */
@NotThreadSafe
public class MutableInteger {
    private int value;

    public int get() {
        return  value;
    }

    public void set(int value) {
        this.value = value;
    }
}
