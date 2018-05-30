package chapter4;

import net.jcip.annotations.NotThreadSafe;

/**
 * 可变的车辆坐标类，不要这么做
 */
@NotThreadSafe
public class MutablePoint {
    public int x;
    public int y;

    public MutablePoint() {
        this.x = 0;
        this.y = 0;
    }

    public MutablePoint(MutablePoint point) {
        this.x = point.x;
        this.y = point.y;
    }
}
