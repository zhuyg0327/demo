package com.test.Thread;

/**
 * 购票线程练习，实现Runnable接口
 */
public class SellTicket implements Runnable {
    //定义10张票
    private int numbers = 10;

    @Override
    public void run() {
        while (true) {
            if (numbers <= 0) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第" + numbers-- + "张票");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();
//        Thread thread=new Thread(sellTicket,"顾客1");
//        thread.start();
        //以上可以简写为
        new Thread(sellTicket, "顾客1").start();
        new Thread(sellTicket, "顾客2").start();
        new Thread(sellTicket, "顾客3").start();

    }
}
