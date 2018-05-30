package chapter4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并不足以保证不变性条件，不要这么做
 */
public class NumberRange {
    //不变性条件 : upper >= lower，upper和lower并非互相独立的关系
    private final AtomicInteger lower = new AtomicInteger(0);//upper的约束条件
    private final AtomicInteger upper = new AtomicInteger(0);//lower的约束聊天

    public void setLower(int i) {
        //不安全的先检查后执行，检查后可能upper已经发生变化
        if(i > upper.get()) {
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        //不安全的先检查后执行，检查后可能lower已经发生变化
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        }
        lower.set(i);
    }
}
