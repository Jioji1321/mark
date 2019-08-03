package concurrent_access.synchronized_method_lock_object;

/**
 * @ClassName Main
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:16
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        MyObject object = new MyObject();
        ThreadA a = new ThreadA(object);
        a.setName("A");
        ThreadB b = new ThreadB(object);
        b.setName("B");
        a.start();
        b.start();
    }
}

