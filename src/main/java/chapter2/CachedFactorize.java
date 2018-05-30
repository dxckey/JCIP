package chapter2;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;

/**
 * 缓存最近执行因数分解的数值以及其计算结果
 */
@ThreadSafe
public class CachedFactorize {
    @GuardedBy(value = "this")
    private BigInteger lastNumber;
    @GuardedBy(value = "this")
    private BigInteger[] lastFactors;
    @GuardedBy(value = "this")
    private long hits;
    @GuardedBy(value = "this")
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    //不要用synchronized修饰方法，会降低性能
    public void service(BigInteger i) {
        BigInteger[] factors = null;
        //若命中之前的执行结果的同步代码块
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        //并不是最近执行因数分解的数值的代码块
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors;
            }
        }
    }

    //因式分解，未完成
    public BigInteger[] factor(BigInteger i) {
        //TODO
        return new BigInteger[]{ BigInteger.valueOf(0), BigInteger.valueOf(0) };
    }
}
