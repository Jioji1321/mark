package concurrent_access.syn_lock_in;

/**
 * @ClassName Service
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/02 0002 16:14
 * @Version 1.0
 **/
public class Service {

    synchronized public void service1(){
        System.out.println("service1");
        service2();
    }

    synchronized public void service2(){
        System.out.println("service2");
        service3();
    }

    synchronized public void service3(){
        System.out.println("service3");
    }
}
