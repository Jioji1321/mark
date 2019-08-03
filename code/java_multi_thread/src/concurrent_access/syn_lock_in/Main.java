package concurrent_access.syn_lock_in;

/**
 * @ClassName Main
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 16:16
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
