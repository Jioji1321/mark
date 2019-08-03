package thread_interrupt.interrput;

/**
 * @ClassName MyThreadInterrupt
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/01 0001 21:51
 * @Version 1.0
 **/
public class MyThreadInterrupt extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (this.interrupted()) {
                    System.out.println("已经是停止状态了，我要退出了");
                    throw new InterruptedException();
                }
                System.out.println("i = " + (i + 1));
            }
            System.out.println("我在for下面");
        } catch (InterruptedException e) {
            System.err.println("进MyThreadInterrupt.java类run方法中的catch了！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            MyThreadInterrupt t = new MyThreadInterrupt();
            t.start();
            Thread.sleep(2000);
            t.interrupt();
        } catch (InterruptedException e) {
            System.err.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
