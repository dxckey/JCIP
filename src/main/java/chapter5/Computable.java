package chapter5;

/**
 *
 * @param <I> 输入类型
 * @param <O> 输出类型
 */
public interface Computable<I, O> {
    O compute(I arg) throws InterruptedException;
}
