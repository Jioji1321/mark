package connect_between_threads.test2;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/12 0012 9:53
 * @Version 1.0
 **/
public class MyThread1 extends Thread {
    private Object lock;

    public MyThread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try{
            synchronized (lock){
                System.out.println("开始   wait time = " + System.currentTimeMillis());
                lock.wait();
                System.out.println("结束   wait time = " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
