package chapter6;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 错误的Timer行为
 */
public class OutOfTime {
    public static void wrongTimer() throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);//该任务执行后，方法结束,Timer会直接结束，不再执行后面的任务
        SECONDS.sleep(1);
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(5);
    }

    static class ThrowTask extends TimerTask {

        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
