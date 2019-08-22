package connect_between_threads.wait_notify_size5;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyList
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/12 0012 21:45
 * @Version 1.0
 **/
public class MyList {
    private static List list =  new ArrayList();

    public static void add(){
        list.add("anyString");
    }

    public static int size(){
        return list.size();
    }

}
