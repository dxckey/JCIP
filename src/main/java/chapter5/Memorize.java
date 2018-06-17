package chapter5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 结果缓存的最终实现
 */
public class Memorize<I, O> implements Computable<I, O> {
    private final ConcurrentMap<I, Future<O>> cache = new ConcurrentHashMap<>();
    private final Computable<I, O> c;

    public Memorize(Computable<I, O> c) {
        this.c = c;
    }

    @Override
    public O compute(I arg) throws InterruptedException {
        while (true) {
            Future<O> future = cache.get(arg);
            if (future == null) {
                Callable<O> eval = () -> c.compute(arg);
                FutureTask<O> futureTask = new FutureTask<>(eval);
                /*等价于：
                * if(!cache.containsKey(arg)) {
                *     return cache.put(arg, futureTask);//返回arg作为key对应的value值
                * } else {
                *     return cache.get(arg);
                * }
                * */
                future = cache.putIfAbsent(arg, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                cache.remove(arg, future);
            } catch (ExecutionException e) {
                throw LaunderThrowable.launderThrowable(e);
            }
        }
    }
}
