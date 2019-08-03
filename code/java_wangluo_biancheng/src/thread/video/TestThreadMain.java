package thread.video;

/**
 * @ClassName TestTread
 * @Description TODO
 * @Author jioji
 * @Date 2019/07/31 0031 21:49
 * @Version 1.0
 **/
public class TestThreadMain {

    public static void main(String[] args) {
        //threadCreate();
        //threadPriority();
        threadYield();
    }

    /**
     * 线程的礼让
     * 运行状态->就绪状态，释放当前获取到的CPU时间片并再次进入抢夺CPU时间片
     */
    public static void threadYield() {
        Runnable r = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);

                if (i == 3) {
                    Thread.yield();
                }
            }
        };

        Thread t1 = new Thread(r, "Thread-1");
        Thread t2 = new Thread(r, "Thread-2");
        Thread t3 = new Thread(r, "Thread-3");


        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 设置优先级
     * 设置优先级只是增加概率，不代表一定能够获取CPU时间片
     */
    public static void threadPriority() {
        MyThread t1 = new MyThread("Thread-1");
        MyThread t2 = new MyThread("Thread-2");

        t1.setPriority(10);
        t2.setPriority(1);

        t1.start();
        t2.start();
    }

    /**
     * 线程的创建
     */
    public static void threadCreate() {
        MyThread t1 = new MyThread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Thread-1");
        MyThread t2 = new MyThread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Thread-2");

        t1.start();
        t2.start();

    }
}


class MyThread extends Thread {
    public MyThread(String name) {
        this.setName(name);
    }

    public MyThread(Runnable r, String name) {
        super(r, name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}
