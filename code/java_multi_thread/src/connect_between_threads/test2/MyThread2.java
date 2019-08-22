package connect_between_threads.test2;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/12 0012 9:53
 * @Version 1.0
 **/
public class MyThread2 extends Thread {
    private Object lock;

    public MyThread2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("开始   notify time = " + System.currentTimeMillis());
            lock.notify();
            System.out.println("结束   notify time = " + System.currentTimeMillis());
        }
    }
}
