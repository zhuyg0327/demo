package com.test.Thread;

import org.apache.commons.lang3.StringUtils;

/**
 * 模拟龟兔赛跑线程
 * 规则，先跑到100的为胜利者
 */
public class Race implements Runnable {
    //定义一个胜利者，因为胜利者只有一个，故用常量表示
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            //模拟兔子睡觉，当兔子跑了10步的时候，让兔子线程休息10毫秒
            if (Thread.currentThread().getName().equals("兔子") && i == 10) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean flag = isGameOver(i);
            if (flag) {
                //如果已经决出胜利者，退出循环
                break;
            }
            System.out.println(Thread.currentThread().getName() + "跑到了第" + i + "步");
        }
    }

    //判断是否结束比赛，先到达100步的为胜利者
    public boolean isGameOver(int steps) {
        boolean type = false;
        //判断是否已经决出胜利者
        if (StringUtils.isNotBlank(winner)) {
            type = true;
        } else {
            if (steps >= 100) {
                winner = Thread.currentThread().getName();
                type = true;
                System.out.println("胜利者是" + winner);
            }
        }
        return type;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "乌龟").start();
        new Thread(race, "兔子").start();
    }
}
