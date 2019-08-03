package concurrent_access.dirty_read;

/**
 * @ClassName ThreadA
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 16:00
 * @Version 1.0
 **/
public class ThreadA extends Thread {
    private PublicVar var;

    public ThreadA(PublicVar var) {
        this.var = var;
    }

    @Override
    public void run() {
        super.run();
        var.setValue("B","BB");
    }
}
