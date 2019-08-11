package concurrent_access.volatile_test.volatile_test_thread;

/**
 * @ClassName Run
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/09 0009 15:51
 * @Version 1.0
 **/
public class Run {
    public static void main(String[] args) {
        MyThread[] myThreadArray = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            myThreadArray[i] = new MyThread();
        }

        for (int i = 0; i < 100; i++) {
            myThreadArray[i].start();
        }
    }
}
