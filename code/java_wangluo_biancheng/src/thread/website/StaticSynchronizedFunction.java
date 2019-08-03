package thread.website;

/**
 * @ClassName StaticSynchronizedFunction
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/01 0001 9:55
 * @Version 1.0
 **/
public class StaticSynchronizedFunction implements Runnable {

    private static int ticket = 100;
    boolean flag = true;

    @Override
    public void run() {

        if (flag) {
            while (true) {
                synchronized (StaticSynchronizedFunction.class) {
                    if (ticket > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                        System.out.println(Thread.currentThread().getName() + " - code : " + ticket--);
                    }
                }
            }
        } else {
            while (true) {
                show();
            }
        }
    }


    public static synchronized void show() {
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }

            System.out.println(Thread.currentThread().getName() + " - show : " + ticket--);
        }
    }
}

class StaticSynchronizedFunctionMain {
    public static void main(String[] args) {
        StaticSynchronizedFunction t = new StaticSynchronizedFunction();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        t.flag = false;
        t2.start();
    }
}


