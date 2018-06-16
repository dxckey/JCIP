package chapter5;

import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用HashMap和同步机制来初始化缓存，并发性不好，不要这么做
 * @param <I> 输入类型
 * @param <O> 输出类型
 */
public class Memorize1<I, O> implements Computable<I, O> {
    @GuardedBy(value = "this")
    private final Map<I, O> cache = new HashMap<>();
    private final Computable<I, O> c;

    public Memorize1(Computable<I, O> c) {
        this.c = c;
    }

    /**
     * 每次只能有一个线程能够执行计算，在某种情况下效率比没有缓存的情况下效率更低:
     * 线程A:-->|计算compute(X)|
     * 线程B:---------------->|计算compute(Y)|
     * 线程C:------------------------------>|取出缓存中的compute(X)|
     * @param arg 需要计算的参数
     * @return 指定类型的返回结果
     * @throws InterruptedException 方法会在同步的情况下调用
     */
    @Override
    public synchronized O compute(I arg) throws InterruptedException {
        O result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
