package thread.website;

/**
 * @ClassName SingleLazy
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/01 0001 10:16
 * @Version 1.0
 **/
public class SingleLazy {

    private static SingleLazy instance = null;

    private SingleLazy() {
    }

    public static SingleLazy getInstance() {
        if (instance == null) {
            synchronized (SingleLazy.class) {
                if (instance == null) {
                    instance = new SingleLazy();
                }
            }
        }
        return instance;
    }
}

/*
class Mythread implements Runnable{

    private static SingleLazy l;

    @Override
    public void run() {
        l = SingleLazy.getInstance();
        System.out.println(l);
    }
}

class Main{
    public static void main(String[] args) {
        Mythread r1 = new Mythread();
        Mythread r2 = new Mythread();

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();



    }
}
*/
