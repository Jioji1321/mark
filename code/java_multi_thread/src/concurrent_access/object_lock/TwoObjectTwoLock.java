package concurrent_access.object_lock;

/**
 * @ClassName TwoObjectTwoLock
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 14:58
 * @Version 1.0
 **/
public class TwoObjectTwoLock {
    public static void main(String[] args) {
        HasSelfPrivateNum numRef1 = new HasSelfPrivateNum();
        HasSelfPrivateNum numRef2 = new HasSelfPrivateNum();
        ThreadA a = new ThreadA(numRef1);
        a.start();
        ThreadB b = new ThreadB(numRef2);
        b.start();
    }
}
