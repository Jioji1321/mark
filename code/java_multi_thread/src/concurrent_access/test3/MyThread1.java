package concurrent_access.test3;

public class MyThread1 extends Thread {

    private Service service;
    private MyObject object;

    public MyThread1(Service service, MyObject object) {
        this.service = service;
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        service.testMethod1(object);
    }
}
