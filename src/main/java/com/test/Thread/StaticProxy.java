package com.test.Thread;

/**
 * 静态代理
 * 婚庆公司帮助男女结婚
 * 代理对象（婚庆公司）和被代理对象（男女）都要实现同一个接口
 * 代理对象要代理真实角色
 */
public class StaticProxy {
    public static void main(String[] args) {
        Person person=new Person("小明");
        WeddingCompany widdingCompany=new WeddingCompany(person);
        widdingCompany.happyMary();
    }
}
    /**
     * 定义一个结婚接口
     */
    interface Marry{
        //实现结婚方法
        void happyMary();
    }

    /**
     * 定义一个结婚对象 人
     * 实现结婚接口
     */
    class Person implements Marry{
        private String name;
        public Person(String name){
            this.name=name;
        }
        //重写结婚方法
        @Override
        public void happyMary() {
            System.out.println(this.name+"要结婚了");
        }
    }

    /**
     * 定义一个婚庆公司  帮助人结婚
     * 实现结婚接口
     */
    class WeddingCompany implements Marry{

        //代理结婚对象
        private Person target;
        public WeddingCompany(Person target){
            this.target=target;
        }
        @Override
        public void happyMary() {
            before();
            //帮助代理对象结婚
            this.target.happyMary();
            after();
        }
        public void before(){
            System.out.println("结婚之前，先布置现场");
        }
        public void after(){
            System.out.println("结婚之后，收尾款");
        }
    }


