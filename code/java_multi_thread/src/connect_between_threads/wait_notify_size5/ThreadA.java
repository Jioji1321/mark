package connect_between_threads.wait_notify_size5;

import java.util.concurrent.SynchronousQueue;

/**
 * @ClassName ThreadA
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/12 0012 21:48
 * @Version 1.0
 **/
public class ThreadA extends Thread {
    public Object object;

    public ThreadA(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        try {
            synchronized (object) {
                if (MyList.size() != 5) {
                    System.out.println("wait begin " + System.currentTimeMillis());
                    object.wait();
                    System.out.println("wait end " + System.currentTimeMillis());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
