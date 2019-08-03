package thread.website;

import java.util.Random;

/**
 * @ClassName MyThread
 * @Description TODO  线程中参数传递，使用回调函数传递数据
 * @Author jioji
 * @Date 2019/08/01 0001 15:34
 * @Version 1.0
 **/
public class MyThread1 extends Thread{

    private Work work;
    public MyThread1(Work work){
        this.work = work;
    }

    @Override
    public void run() {
        Random random = new Random();
        Data data = new Data();
        int i1 = random.nextInt(100);
        int i2 = random.nextInt(200);
        int i3 = random.nextInt(300);
        work.process(data,i1,i2,i3); //使用回调函数
        System.out.println(String.valueOf(i1) + "," + String.valueOf(i2) + "," + String.valueOf(i3));
    }

    public static void main(String[] args) {
        Thread t1 = new MyThread1(new Work());
        t1.start();
    }
}


class Data{
    public int data = 0;
}

class Work{
    public void process(Data data,Integer... numbers){
        for(int i: numbers){
            data.data += i;
        }
    }
}