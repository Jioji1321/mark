package concurrent_access.test2;


public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        MyObject object = new MyObject();

        MyThread1 a = new MyThread1(service,object);
        MyThread2 b = new MyThread2(object);

        a.setName("Thread-A");
        b.setName("Thread-B");

        a.start();
        b.start();
    }
}
