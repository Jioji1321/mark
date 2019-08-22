package connect_between_threads.test1;

/**
 * @ClassName Main
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/12 0012 9:41
 * @Version 1.0
 **/
public class MainThrowException {
    public static void main(String[] args) {
        try {
            String newString = new String("");
            newString.wait();

            /**
             * 出现异常的原因是没有对象监视器，也就是没有同步加锁
             */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
