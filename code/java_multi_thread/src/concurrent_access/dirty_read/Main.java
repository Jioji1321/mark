package concurrent_access.dirty_read;

/**
 * @ClassName Main
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 16:01
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        try {
            PublicVar var = new PublicVar();
            ThreadA thread = new ThreadA(var);
            thread.start();
            Thread.sleep(2000);
            var.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
