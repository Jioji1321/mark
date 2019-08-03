package thread_interrupt.sleep_interrupt;

/**
 * @ClassName ThreadInterruptSleep
 * @Description TODO 先interrupt在sleep
 * @Author jioji
 * @Date 2019/08/02 0002 9:30
 * @Version 1.0
 **/
public class ThreadInterruptSleep extends Thread{

    @Override
    public void run() {
        super.run();
        try{
            for (int i = 0; i < 100000; i++) {
                System.out.println("i = " + (i+1));
            }
            System.out.println("run begin");
            Thread.sleep(20000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.err.println("先停止再遇到sleep，进入catch!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadInterruptSleep t = new ThreadInterruptSleep();
        t.start();
        t.interrupt();
        System.out.println("end!");
    }
}
