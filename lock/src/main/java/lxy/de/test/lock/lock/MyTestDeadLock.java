package lxy.de.test.lock.lock;

public class MyTestDeadLock {

    public static void main(String[] args) {
        Object girl = new Object();
        Object money = new Object();
        Boy boy = new Boy(girl, money);
        BadMan badMan = new BadMan(girl, money);
//        boy.run();
//        badMan.run();
        boy.start();
        badMan.start();
    }

}


class Boy extends Thread{
    private Object girl;
    private Object money;

    public Boy(Object girl,Object money) {
        this.girl = girl;
        this.money = money;
    }

    public void run(){
        synchronized (girl){
            System.out.println("放人");
            synchronized (money){
                System.out.println("给钱");
            }
        }
    }

    public void startabc(){
        System.out.println("哈哈哈");
    }
}

class BadMan extends Thread{
    private Object girl;
    private Object money;

    public BadMan(Object girl,Object money) {
        this.girl = girl;
        this.money = money;
    }

//    public void run(Object girl){
//        synchronized (money){
//            System.out.println("拿钱来");
//            synchronized (girl){
//                System.out.println("我放了你");
//            }
//        }
//    }

    public void run(Object girl){
        synchronized (money){
            System.out.println("拿钱来");
            synchronized (girl){
                System.out.println("我放了你");
            }
        }
    }
}