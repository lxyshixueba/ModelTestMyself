package lxy.de.test.lock.lock;

public class RunnableTest {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("张飞");
        }).start();

        new Runnable() {
            @Override
            public void run() {
                      
            }
        };
    }
}
