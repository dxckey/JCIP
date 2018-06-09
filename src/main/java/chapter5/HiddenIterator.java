package chapter5;

import net.jcip.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 隐藏在字符串连接中的迭代操纵，不要这么做
 */
public class HiddenIterator {
    @GuardedBy(value = "this")
    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            set.add(r.nextInt());
        }
        /*
        * set.toString()会对容器进行迭代，在迭代期间容器发生变化会抛出ConcurrentModificationException，所以需要获取到HiddenIterator
        * 容器的hashCode()和equals()也会间接的执行容器迭代，同样的容器作为另一个容器的元素或者键值，如containsAll，removeAll等方法
        * */
        System.out.println("DEBUG: added ten elements to " + set);
    }
}
