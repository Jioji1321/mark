package concurrent_access.test3;

public class MyObject {

    public void speedPrintString(){
        synchronized (this){
        System.out.println("speedPrintString ___ get lock Time=" + System.currentTimeMillis() + " run Thread Name = " + Thread.currentThread().getName());
            System.out.println("--------------------------------");
            System.out.println("speedPrintString ___ release lock Time=" + System.currentTimeMillis() + " run Thread Name = " + Thread.currentThread().getName());
        }
    }
}
