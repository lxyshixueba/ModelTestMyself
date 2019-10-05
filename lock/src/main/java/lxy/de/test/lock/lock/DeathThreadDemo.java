package lxy.de.test.lock.lock;

public class DeathThreadDemo {
    public static void main(String[] args) {
        DeadLock dt0=new DeadLock(0);
        DeadLock dt1=new DeadLock(1);
        new Thread(dt0).start();
        new Thread(dt1).start();
    }
}


class DeadLock implements Runnable{
    private int value;
    private static Object o1 = new Object(), o2 = new Object();
    public DeadLock(int value){
        this.value=value;
    }
    public void run() {
        if(value==0){
            synchronized(o1){
                try {
                    Thread.sleep(3000);
                    for (int i = 11; i < 20; i++) {
                        System.out.println("o1"+i);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                synchronized(o2){
                    System.out.println("o2"+value);
                }
            }

        }
        if(value==1){
            synchronized(o2){
                try {
                    Thread.sleep(3000);
                    for (int i = 1; i < 10; i++) {
                        System.out.println("o2"+i);
                    }

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                synchronized(o1){
                    System.out.println("o1"+value);
                }
            }

        }
    }
}