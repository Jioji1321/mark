package concurrent_access.object_lock;

/**
 * @ClassName ThreadB
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:01
 * @Version 1.0
 **/
public class ThreadB extends Thread {
    private HasSelfPrivateNum numRef;

    public ThreadB(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("b");
    }
}