package chapter5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用ConcurrentHashMap替换HashMap初始化缓存，存在漏洞，不要这么做
 * @param <I> 输入类型
 * @param <O> 输出类型
 */
public class Memorize2<I, O> implements Computable<I, O> {
    private final Map<I, O> cache = new ConcurrentHashMap<>();
    private final Computable<I, O> c;

    public Memorize2(Computable<I, O> c) {
        this.c = c;
    }

    /**
     * 如果两个线程在计算同一个值(计算的开销很大)，两个线程互相不知道计算正在进行，就会发生重复计算：
     * 线程A:-->|compute(X)不在缓存中|-->|      计算compute(X)      |-->|compute(X)放入缓存|
     * 线程B:--------------------------->|compute(X)不在缓存中|----->|      计算compute(X)      |-->|compute(X)放入缓存|
     * @param arg 需要计算的参数
     * @return 指定类型的返回结果
     * @throws InterruptedException 方法会在并发的情况下调用
     */
    @Override
    public O compute(I arg) throws InterruptedException {
        O result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
