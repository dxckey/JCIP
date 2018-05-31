package chapter2;

import net.jcip.annotations.NotThreadSafe;

/**
 * 延迟初始化中的竞态条件，不要这么做
 */
@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }
}

class ExpensiveObject {

}