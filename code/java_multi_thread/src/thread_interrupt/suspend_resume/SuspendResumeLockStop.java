package thread_interrupt.suspend_resume;

/**
 * @ClassName SuspendResumeLockStop
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 11:27
 * @Version 1.0
 **/
public class SuspendResumeLockStop extends Thread {
    private long i = 0;

    @Override
    public void run() {
        while(true){
            i++;
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        try{
            SuspendResumeLockStop t = new SuspendResumeLockStop();
            t.start();
            Thread.sleep(1000);
            t.suspend();
            System.out.println("main end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
