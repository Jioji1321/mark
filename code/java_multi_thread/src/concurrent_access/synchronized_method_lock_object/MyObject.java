package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName MyObject
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:12
 * @Version 1.0
 **/
public class MyObject {

    synchronized public void methodA() {
        try {
            System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end time: " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void methodB() {
        try {
            System.out.println("begin methodB threadName=" + Thread.currentThread().getName() + ", begin time: " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
