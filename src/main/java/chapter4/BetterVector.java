package chapter4;

import net.jcip.annotations.ThreadSafe;

import java.util.Vector;

/**
 * 扩展Vector并增加一个方法
 * @param <E>
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E e) {
        boolean absent = !contains(e);
        if (absent) {
            add(e);
        }
        return absent;
    }
}
