package concurrent_access.inner_test1;

/**
 * @ClassName Run
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/09 0009 14:43
 * @Version 1.0
 **/
public class Run {
    public static void main(String[] args) {
        final OutClass.Inner inner = new OutClass.Inner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                inner.method1();
            }
        },"a");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                inner.method2();
            }
        },"b");

        t1.start();
        t2.start();
    }
}
