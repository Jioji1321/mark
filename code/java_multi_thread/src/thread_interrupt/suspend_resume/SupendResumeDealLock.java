package thread_interrupt.suspend_resume;

/**
 * @ClassName SupendResumeDealLock
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 11:20
 * @Version 1.0
 **/
public class SupendResumeDealLock {

    synchronized public void printString(){
        System.out.println("begin");
        if(Thread.currentThread().getName().equals("a")){
            System.out.println("a线程永远suspend了");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        try{
            final SupendResumeDealLock object = new SupendResumeDealLock();
            Thread t = new Thread(){
                @Override
                public void run() {
                    object.printString();
                }
            };
            t.setName("a");
            t.start();
            Thread.sleep(1000);
            Thread t2 = new Thread(){
                @Override
                public void run() {
                    System.out.println("Thread2启动了，但是进入不了printString()，只打印1个begin");
                    System.out.println("因为printString()方法被a线程锁定并且永远suspend暂停了");
                    object.printString();
                }
            };
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
