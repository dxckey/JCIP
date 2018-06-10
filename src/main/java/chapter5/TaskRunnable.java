package chapter5;

import java.util.concurrent.BlockingQueue;

/**
 * 回复被中断的状态以避免屏蔽中断
 */
public class TaskRunnable implements Runnable {
    BlockingQueue<Task> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            //恢复被中断的状态
            Thread.currentThread().interrupt();
        }
    }

    public void processTask(Task task) {
        //处理任务
    }

    interface Task {

    }
}
