package chapter5;

import java.util.Vector;

/**
 * 在使用客户端枷锁的Vector上的复合操作
 */
public class SafeVectorHelpers {
    public static Object getLast(Vector vector) {
        synchronized (vector) {
            int lastIndex = vector.size() - 1;
            return vector.get(lastIndex);
        }
    }

    public static void deleteLast(Vector vector) {
        synchronized (vector) {
            int lastIndex = vector.size() - 1;
            vector.remove(lastIndex);
        }
    }
}
