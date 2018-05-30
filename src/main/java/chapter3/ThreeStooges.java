package chapter3;

import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * 在可变对象的基础上构建不可变对象
 * 不可变对象必要条件：
 * 1.对象创建后其转台就不能修改
 * 2.对象的所有域都是final类型
 * 3.对象是被正确创建的（在对象创建期间，this引用没有逸出）
 */
@Immutable
public final class ThreeStooges {
    /**
     * stooges保存的对象是可变的，但是由于程序的设计在对象构造完成后，无法修改stooges对象
     */
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
