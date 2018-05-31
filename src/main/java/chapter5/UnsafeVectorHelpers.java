package chapter5;

import java.util.Vector;

/**
 * Vector上可能会导致混乱结果的复合操作
 */
public class UnsafeVectorHelpers {
    public static Object getLast(Vector vector) {
        int lastIndex = vector.size() - 1;
        return vector.get(lastIndex);
    }

    public static void deleteLast(Vector vector) {
        int lastIndex = vector.size() - 1;
        vector.remove(lastIndex);
    }
}
