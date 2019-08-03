package thread_interrupt.interrput;

/**
 * @ClassName MyThread
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/01 0001 21:32
 * @Version 1.0
 **/
public class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (this.interrupted()) {
                System.out.println("已经是停止状态了，我要退出了");
                break;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("如果此代码是fot又继续运行，线程并未停止");
    }

    public static void main(String[] args) {
        try{
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(2000);

            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
