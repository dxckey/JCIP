package chapter3;

import java.util.HashSet;
import java.util.Set;

/**
 * 发布对象
 */
public class Secrets {
    public static Set<Secrets> knowSecrets;

    public void initialize() {
        knowSecrets = new HashSet<>();
    }
}
