package connect_between_threads.two_threads_trans_data;

/**
 * @ClassName MyThreadA
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/11 0011 14:17
 * @Version 1.0
 **/
public class MyThreadA extends Thread {
    private MyList list;

    public MyThreadA(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                list.add();
                System.out.println("添加了" + (i + 1) + "个元素");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
