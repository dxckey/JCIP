package chapter3;

public class UnsafeStates {
    private String[] states = { "AK", "AL" };

    //数组states逸出了当前的作用域，本该私有的states被发布，任何调用者都可以通过该方法获取数组的引用修改数组
    public String[] getStates() {
        return states;
    }
}
