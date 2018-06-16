package chapter5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界，Semaphore中管理着一组许可
 * Semaphore不会将许可和线程相关联，可以将acquire视为消费一个许可,
 * release可以视为创建一个许可，Semaphore并不受限于创建时的初始许可数量
 * @param <T>
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        semaphore.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(t);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                semaphore.release();
            }
        }
    }

    public boolean remove(T t) {
        boolean wasRemoved = set.remove(t);
        if (wasRemoved) {
            semaphore.release();
        }
        return wasRemoved;
    }
}
