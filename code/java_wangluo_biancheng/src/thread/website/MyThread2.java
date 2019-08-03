package thread.website;

/**
 * @ClassName MyThread2
 * @Description TODO 线程参数传递：通过变量和方法传递参数
 * @Author jioji
 * @Date 2019/08/01 0001 15:45
 * @Version 1.0
 **/
public class MyThread2 implements Runnable{
    private String name;

    public void setName(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Hello, " + name);
    }

    public static void main(String[] args) {
        MyThread2 r = new MyThread2();
        r.setName("world");

        Thread t = new Thread(r);
        t.start();
    }
}


