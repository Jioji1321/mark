package concurrent_access.syn_lock_in;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 16:16
 * @Version 1.0
 **/
public class MyThread extends Thread {
    @Override
    public void run() {
        Service service = new Service();
        service.service1();
    }

}

