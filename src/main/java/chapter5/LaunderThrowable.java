package chapter5;

/**
 * 强制将未检查的Throwable转换为RuntimeException
 */
public class LaunderThrowable {

    /**
     * 如果是Throwable是Error，那么抛出它；
     * 如果是RuntimeException，那么返回它；
     * 如果以上情况都不是，那么抛出IllegalStateException
     * @param throwable 异常对象
     * @return RuntimeException对象
     */
    public static RuntimeException launderThrowable(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        } else if (throwable instanceof Error) {
            throw (Error) throwable;
        } else {
            throw new IllegalStateException("Not unchecked", throwable);
        }
    }
}
