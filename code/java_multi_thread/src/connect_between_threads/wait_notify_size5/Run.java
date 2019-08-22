package connect_between_threads.wait_notify_size5;

/**
 * @ClassName Run
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/12 0012 21:55
 * @Version 1.0
 **/
public class Run {
    public static void main(String[] args) {
        try{
            Object lock = new Object();
            ThreadA a = new ThreadA(lock);
            a.start();
            Thread.sleep(3000);
            ThreadB b = new ThreadB(lock);
            b.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
