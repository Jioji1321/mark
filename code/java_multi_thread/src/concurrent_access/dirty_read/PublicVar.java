package concurrent_access.dirty_read;

/**
 * @ClassName PublicVar
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 15:56
 * @Version 1.0
 **/
public class PublicVar {

    public String username = "A";
    public String password = "AA";

    synchronized public void setValue(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method thread name = "
                    + Thread.currentThread().getName() + ", username = "
                    + username + ", password = " + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void getValue() {
        System.out.println("setValue method thread name = "
                + Thread.currentThread().getName() + ", username = "
                + username + ", password = " + password);
    }
}
