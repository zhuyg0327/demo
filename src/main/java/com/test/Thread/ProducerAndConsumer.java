package com.test.Thread;


import java.util.ArrayList;
import java.util.List;

/**
 * 生产者和消费者模式
 * 管程法（通过变量值去控制)
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        Container container = new Container();
        Producer producer = new Producer(container);
        Consumer consumer = new Consumer(container);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

/**
 * 生产者
 */
class Producer extends Thread {
    //容器
    private Container container;

    public Producer(Container container) {
        this.container = container;
    }

    //生产食物
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了第" + i + "只鸡");
            container.puch(new Chiken(i));
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread {
    private Container container;

    public Consumer(Container container) {
        this.container = container;
    }

    /**
     * 消费者消费食物
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Chiken chiken = container.pop();
            int id = chiken.getId();
            if (id >= 0) {
                System.out.println("消费者消费到了第" + id + "只鸡");
            } else {
                System.out.println("请等待生产");
            }

        }
    }
}

/**
 * 食物，鸡
 */
class Chiken {
    //产品编号
    private int id;

    public Chiken(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

/**
 * 容器
 */
class Container {
    List<Chiken> chikens = new ArrayList<>();
    int count = 0;

    /**
     * 生产者向容器中放入食物
     */
    public synchronized void puch(Chiken chiken) {
        if (count >= 15) {
            //容器满了，需要通知消费者消费，生产者等待
            try {
                this.wait();
                System.out.println(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            chikens.add(chiken);
            count++;

            //通知生产者继续生产消费者消费
            this.notify();
        }
    }

    /**
     * 消费者从容器里拿出食物
     */
    public synchronized Chiken pop() {
        System.out.println("此时数组有" + chikens.size() + "个元素,count是" + count);
        Chiken chiken = new Chiken(-1);
        //判断有没有食物
        if (count <= 0) {
            //没有食物，通知消费者等待，生产者继续生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            count--;
            chiken = chikens.get(count);
            //否则消费
            chikens.remove(count);
            this.notify();
        }
        return chiken;
    }
}

