package com.test.Thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 多线程实现图片下载,继承thread类
 */
public class Thread1 extends Thread {
    //
    private String url;
    private String name;

    public Thread1(String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        //下载图片
        WebDownloader webDownloader = new WebDownloader();//下载器
        webDownloader.downloader(url,name);//下载文件的方式
        System.out.println("下载了图片-->"+name);

    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1("https://img0.baidu.com/it/u=1536097492,1606771850&fm=26&fmt=auto&gp=0.jpg","你好1.jpg");
        Thread1 t2 = new Thread1("https://img1.baidu.com/it/u=3247448385,1361838491&fm=26&fmt=auto&gp=0.jpg","你好2.jpg");
        Thread1 t3 = new Thread1("https://img2.baidu.com/it/u=1025063961,2298343961&fm=26&fmt=auto&gp=0.jpg","你好3.jpg");

        t1.start();
        System.out.println("执行了t1");
        t2.start();
        System.out.println("执行了t2");
        t3.start();
        System.out.println("执行了t3");
    }
}

//下载图片
class WebDownloader{

    //下载方法
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            //输出异常信息
            System.out.println("downloader方法出现异常");
        }
    }
}
