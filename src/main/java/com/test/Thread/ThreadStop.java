package com.test.Thread;

/**
 * 线程停止练习
 */
public class ThreadStop implements Runnable {
    //定义一个标志，当标志位false时停止线程
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("主线程跑到了第" + i++ + "个数");
        }
    }

    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        ThreadStop threadStop = new ThreadStop();
        new Thread(threadStop).start();

        for (int i = 0; i < 1100; i++) {
            System.out.println("main"+i);
            if (i == 1090) {
                threadStop.stop();
                System.out.println("线程该停止了");
            }
        }
    }
}
