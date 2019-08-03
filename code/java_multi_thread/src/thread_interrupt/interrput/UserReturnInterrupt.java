package thread_interrupt.interrput;

/**
 * @ClassName UserReturnInterrupt
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 9:52
 * @Version 1.0
 **/
public class UserReturnInterrupt extends Thread {

    @Override
    public void run() {
        while(true){
            if(this.isInterrupted()){
                System.out.println("停止了");
                return;
            }
            System.out.println("timer = " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args){
        UserReturnInterrupt t = new UserReturnInterrupt();
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
    }
}
