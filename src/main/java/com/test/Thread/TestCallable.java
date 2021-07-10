package com.test.Thread;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//JUC并发编程
import java.util.concurrent.*;

public class TestCallable implements Callable<Boolean> {

    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        //下载图片
        WebDownloaders webDownloader = new WebDownloaders();//下载器
        webDownloader.downloader(url, name);//下载文件的方式
        System.out.println("下载了图片-->" + name);
        return true;
    }

    //启动线程
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://img0.baidu.com/it/u=1536097492,1606771850&fm=26&fmt=auto&gp=0.jpg", "你好1.jpg");
        TestCallable t2 = new TestCallable("https://img1.baidu.com/it/u=3247448385,1361838491&fm=26&fmt=auto&gp=0.jpg", "你好2.jpg");
        TestCallable t3 = new TestCallable("https://img2.baidu.com/it/u=1025063961,2298343961&fm=26&fmt=auto&gp=0.jpg", "你好3.jpg");

        //创建执行服务：
        ExecutorService ser = Executors.newFixedThreadPool(3);

        //提交执行：
        Future<Boolean> result1 = ser.submit(t1);
        Future<Boolean> result2 = ser.submit(t2);
        Future<Boolean> result3 = ser.submit(t3);

        //获取结果
        boolean r1 = result1.get();
        boolean r2 = result2.get();
        boolean r3 = result3.get();

        //判断线程是否顺利结束或者有异常

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);

        //关闭服务
        ser.shutdownNow();


    }


}


//下载图片
class WebDownloaders {
    //下载方法
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            //输出异常信息
            System.out.println("downloader方法出现异常");
        }
    }

}
