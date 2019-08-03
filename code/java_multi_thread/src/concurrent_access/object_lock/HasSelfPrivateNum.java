package concurrent_access.object_lock;

/**
 * @ClassName HasSelfPrivateNum
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 14:45
 * @Version 1.0
 **/
public class HasSelfPrivateNum {

    private int num = 0;

    synchronized public void addI(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(username + " num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}