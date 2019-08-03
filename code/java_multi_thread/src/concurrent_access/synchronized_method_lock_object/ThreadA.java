package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName ThreadA
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:14
 * @Version 1.0
 **/
public class ThreadA extends Thread {
    private MyObject object;

    public ThreadA(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodA();
    }
}
