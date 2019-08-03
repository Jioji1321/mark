package thread.video;

/**
 * @ClassName SourceConflict
 * @Description TODO
 * @Author jioji
 * @Date 2019/07/31 0031 22:51
 * @Version 1.0
 **/
public class SourceConflict {
    public static void main(String[] args) {

        Runnable r = () -> {
            while (Ticket.restTicket > 0) {
                //System.out.println(Thread.currentThread().getName() + " 卖出了1张票，还剩余" + --Ticket.restTicket + "张票！");
                //相当于：
                Ticket.restTicket = Ticket.restTicket - 1;
                System.out.println(Thread.currentThread().getName() + " 卖出了1张票，还剩余" + Ticket.restTicket + "张票！");
            }
        };

        Thread t1 = new Thread(r, "Thread - 1");
        Thread t2 = new Thread(r, "Thread - 2");
        Thread t3 = new Thread(r, "Thread - 3");
        Thread t4 = new Thread(r, "Thread - 4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class Ticket {
    public static int restTicket = 100;
}