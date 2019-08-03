package concurrent_access.object_lock;

/**
 * @ClassName ThreadA
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:00
 * @Version 1.0
 **/
public class ThreadA extends Thread {
    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("a");
    }
}