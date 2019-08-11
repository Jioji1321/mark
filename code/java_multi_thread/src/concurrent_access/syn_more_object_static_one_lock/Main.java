package concurrent_access.syn_more_object_static_one_lock;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        ThreadA a = new ThreadA(service);
        a.setName("Thread-A");
        a.start();

        ThreadB b = new ThreadB(service);
        b.setName("Thread-B");
        b.start();


    }
}