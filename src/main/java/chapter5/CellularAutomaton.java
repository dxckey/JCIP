package chapter5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 通过CyclicBarrier协调细胞自动衍生系统中的计算
 */
public class CellularAutomaton {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomaton(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, this.mainBoard::commitNewValues);
        this.workers = new Worker[count];
        for (int i = 0; i < this.workers.length; i++) {
            this.workers[i] = new Worker(this.mainBoard.getSubBoard(count, i));
        }
    }

    public void start() {
        for (Worker worker : workers) {
            new Thread(worker).start();
        }
        this.mainBoard.waitForConvergence();
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker (Board board) {
            this.board = board;
        }

        @Override
        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }
                try {
                    barrier.await();//等待其他线程
                }catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

        private int computeValue(int x, int y) {
            // 计算新的值
            return 0;
        }
    }

    interface Board {
        int getMaxX();
        int getMaxY();
        int getValue(int x, int y);
        int setNewValue(int x, int y, int value);
        void commitNewValues();
        boolean hasConverged();
        void waitForConvergence();
        Board getSubBoard(int numPartitions, int index);
    }
}
