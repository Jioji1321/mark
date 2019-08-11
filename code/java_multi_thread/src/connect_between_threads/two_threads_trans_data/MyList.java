package connect_between_threads.two_threads_trans_data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyList
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/11 0011 14:14
 * @Version 1.0
 **/
public class MyList {
    private List list = new ArrayList();
    public void add(){
        list.add("test data");
    }

    public int size(){
        return list.size();
    }
}
