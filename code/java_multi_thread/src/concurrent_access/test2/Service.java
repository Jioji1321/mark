package concurrent_access.test2;

public class Service {

    public void testMethod1(MyObject object){
        synchronized (object){
            try{
                System.out.println("speedPrintString ___ get lock Time=" + System.currentTimeMillis() + " run Thread Name = " + Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println("speedPrintString ___ release lock Time=" + System.currentTimeMillis() + " run Thread Name = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
