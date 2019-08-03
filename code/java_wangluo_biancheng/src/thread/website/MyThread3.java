package thread.website;

/**
 * @ClassName MyThread3
 * @Description TODO 线程参数传递：通过构造方法传递参数
 * @Author jioji
 * @Date 2019/08/01 0001 15:50
 * @Version 1.0
 **/
public class MyThread3 implements Runnable{
    private String name;

    public MyThread3(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Hello, " + name);
    }


    public static void main(String[] args) {
        MyThread3 r = new MyThread3("world");
        Thread t = new Thread(r);
        t.start();
    }
}
