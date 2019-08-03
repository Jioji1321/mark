package thread.website;

/**
 * @ClassName SingleHungry
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/01 0001 10:13
 * @Version 1.0
 **/
public class SingleHungry {

    private static SingleHungry instance = new SingleHungry();

    private SingleHungry(){}

    public static SingleHungry getInstance(){
        return instance;
    }
}
