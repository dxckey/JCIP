package chapter3;

public class NoVisibility {
    private static boolean ready;
    private static int number;


    public static void readAndPrint() {
        Thread th = new Thread(() -> {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        });
        th.start();//可能会一直无法读取到number
        /*
        * 在没有同步的情况下，线程可能无法读取到number的值，
        * 可能打印出0（由于编译器，处理器以及运行时的重排序，ready的赋值操作也许会在number的赋值操作之前执行）
        * */
        number = 42;
        ready = true;
    }

}
