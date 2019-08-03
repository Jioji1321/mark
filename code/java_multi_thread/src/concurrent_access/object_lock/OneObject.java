package concurrent_access.object_lock;

/**
 * @ClassName OneObject
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:02
 * @Version 1.0
 **/
public class OneObject {
    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        ThreadA a = new ThreadA(numRef);
        a.start();
        ThreadB b = new ThreadB(numRef);
        b.start();
    }
}
