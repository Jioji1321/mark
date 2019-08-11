package connect_between_threads.two_threads_trans_data;

/**
 * @ClassName MyThreadB
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/11 0011 14:19
 * @Version 1.0
 **/
public class MyThreadB extends Thread {
    private MyList list;

    public MyThreadB(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        try{
            while(true){
                if(list.size() == 5){
                    System.out.println("等于5了，该退出了");
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
