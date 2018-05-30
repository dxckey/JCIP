package chapter3;

/**
 * 没有足够同步的情况下发布对象，不安全
 */
public class StuffIntoPublic {
    //不安全的发布
    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}
