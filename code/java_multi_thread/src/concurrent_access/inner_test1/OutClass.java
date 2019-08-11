package concurrent_access.inner_test1;

/**
 * @ClassName OutClass
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/09 0009 14:40
 * @Version 1.0
 **/
public class OutClass {
    static class Inner{
        public void method1(){
            synchronized("其他的锁"){
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " i = " + i);
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }

        public synchronized void method2(){
            for (int i = 11; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " i = " + i);
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

}
