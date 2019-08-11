package concurrent_access.test2;

public class MyObject {

    synchronized public void speedPrintString(){
        System.out.println("speedPrintString ___ get lock Time=" + System.currentTimeMillis() + " run Thread Name = " + Thread.currentThread().getName());
        System.out.println("--------------------------------");
        System.out.println("speedPrintString ___ release lock Time=" + System.currentTimeMillis() + " run Thread Name = " + Thread.currentThread().getName());
    }
}
