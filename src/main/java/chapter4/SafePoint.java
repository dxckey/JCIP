package chapter4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * 线程安全的可变车辆坐标类
 */
@ThreadSafe
public class SafePoint {
    @GuardedBy(value = "this")
    private int x;
    @GuardedBy(value = "this")
    private int y;

    private SafePoint(int[] array) {
        this(array[0], array[1]);
    }

    public SafePoint(SafePoint point) {
        this(point.get());
        /*this(point.x, point.y);//会产生竞态条件*/
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[] { x, y };
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
