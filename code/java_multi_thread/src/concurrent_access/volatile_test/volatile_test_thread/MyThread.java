package concurrent_access.volatile_test.volatile_test_thread;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/09 0009 15:48
 * @Version 1.0
 **/
public class MyThread extends Thread {
    volatile public static int count;

    /**
     * 注意一定要加上static关键字
     * 这样synchronized与static锁的内容就是MyThread.class类了
     * 也就达到同步的效果了
     */
    synchronized private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count = " + count);
    }

    @Override
    public void run() {
        addCount();
    }
}
