package chapter3;

public class Holder {
    private int n;

    public Holder(int n) {//Object构造器在该构造器之前运行，将所有成员写入默认值
        this.n = n;
    }

    public void assertSanity() {
        if (n != n){
            throw new AssertionError("这个表达式是错误的");
        }
    }
}
