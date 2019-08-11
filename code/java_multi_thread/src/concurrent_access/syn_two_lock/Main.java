package concurrent_access.syn_two_lock;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        ThreadA a = new ThreadA(service);
        a.setName("Thread-A");
        a.start();

        ThreadB b = new ThreadB(service);
        b.setName("Thread-B");
        b.start();

        ThreadC c = new ThreadC(service);
        c.setName("Thread-C");
        c.start();
    }
}
