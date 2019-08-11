package concurrent_access.test3;

public class MyThread2 extends Thread {
    private MyObject object;

    public MyThread2(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.speedPrintString();
    }
}
