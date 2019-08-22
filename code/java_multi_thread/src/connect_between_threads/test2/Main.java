package connect_between_threads.test2;

/**
 * @ClassName Main
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/12 0012 9:57
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        try{
            Object lock = new Object();
            MyThread1 thread1 = new MyThread1(lock);
            thread1.start();
            Thread.sleep(3000);
            MyThread2 thread2 = new MyThread2(lock);
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
