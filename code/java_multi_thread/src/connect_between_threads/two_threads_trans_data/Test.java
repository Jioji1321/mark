package connect_between_threads.two_threads_trans_data;

/**
 * @ClassName Test
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/11 0011 14:21
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        MyList service = new MyList();

        MyThreadA a = new MyThreadA(service);
        a.setName("a");
        a.start();

        MyThreadB b = new MyThreadB(service);
        b.setName("b");
        b.start();
    }
}
