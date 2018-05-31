package chapter4;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 通过客户端加锁实现同步
 * @param <E>
 */
@ThreadSafe
public class GoodListHelper<E> {
    public final List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E e) {
        synchronized(list) {
            boolean absent = !list.contains(e);
            if (absent)
                list.add(e);
            return absent;
        }
    }
}
