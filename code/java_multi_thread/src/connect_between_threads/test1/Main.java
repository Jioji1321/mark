package connect_between_threads.test1;

/**
 * @ClassName Main
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/12 0012 9:41
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        try {
            String lock = new String();
            System.out.println("syn上面");
            synchronized (lock) {
                System.out.println("syn第一行");
                lock.wait();
                System.out.println("wait下面的代码");
            }
            System.out.println("syn下面的代码");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
