package concurrent_access.volatile_test.test1;

/**
 * @ClassName PrintString
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/09 0009 15:14
 * @Version 1.0
 **/
public class PrintString {

    private boolean isContinuePrint = true;
    public boolean isContinuePrint(){
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }

    public void printStringMethod(){
        try{
            while(isContinuePrint){
                System.out.println("run printStringMethod name = " + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
