package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName ThreadB
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:15
 * @Version 1.0
 **/
public class ThreadB extends Thread {
    private MyObject object;

    public ThreadB(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodB();
    }
}
