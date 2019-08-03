package thread_interrupt.sleep_interrupt;

/**
 * @ClassName ThreadSleepInterrupt
 * @Description TODO 先sleep再interrupt
 *                   在sleep状态下停止某一线程，会进入catch块，并且清除停止状态值，使之变为false
 * @Author jioji
 * @Date 2019/08/02 0002 9:13
 * @Version 1.0
 **/
public class ThreadSleepInterrupt extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.err.println("在休眠中被停止，进入catch : " + this.isInterrupted());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            ThreadSleepInterrupt t = new ThreadSleepInterrupt();
            t.start();
            Thread.sleep(200);
            t.interrupt();
        } catch (InterruptedException e) {
            System.err.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
