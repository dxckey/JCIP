package chapter4;

import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NotThreadSafe
public class BadListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    /*
    * 该方法持有的锁是BadListHelper本身而不是list对象,需要同步对的对象是list而不是BadListHelper本身，
    * list本身不是私有的域，外部线程可以直接得到list的引用，因此该方法对list的操作不具备原子性
    * */
    public synchronized boolean putIfAbsent(E e) {
        boolean absent = !list.contains(e);
        if (absent)
            list.add(e);
        return absent;
    }
}
