package chapter5;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 基于FutureTask的缓存封装器，有良好的并发现(归功于ConcurrentHashMap的高并发性)
 * @param <I> 输入类型
 * @param <O> 输出类型
 */
public class Memorize3<I, O> implements Computable<I, O> {
    private final Map<I, Future<O>> cache = new ConcurrentHashMap<>();
    private final Computable<I, O> c;

    public Memorize3(Computable<I, O> c) {
        this.c = c;
    }

    /**
     * 虽然触发概率不高，仍然存在两个线程计算同一个值的漏洞：
     * 线程A:-->|compute(X)不在缓存中|-->|将计算compute(X)的Future放入缓存|-->|计算compute(X)|-->|设置结果|
     * 线程B:---->|compute(X)不在缓存中|-->|将计算compute(X)的Future放入缓存|-->|计算compute(X)|-->|设置结果|
     * @param arg 需要计算的参数
     * @return 指定类型的返回结果
     * @throws InterruptedException 方法会在并发的情况下调用
     */
    @Override
    public O compute(I arg) throws InterruptedException {
        Future<O> future = cache.get(arg);
        if (future == null) {
            Callable<O> eval = () -> c.compute(arg);
            FutureTask<O> futureTask = new FutureTask<O>(eval);
            future = futureTask;
            cache.put(arg, futureTask);
            futureTask.run();
        }
        try {
            return future.get();
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e);
        }
    }
}
