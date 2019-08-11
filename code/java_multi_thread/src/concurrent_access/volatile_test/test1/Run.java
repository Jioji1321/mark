package concurrent_access.volatile_test.test1;

/**
 * @ClassName Run
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/09 0009 15:17
 * @Version 1.0
 **/
public class Run {
    public static void main(String[] args) {
        PrintString printString = new PrintString();
        printString.printStringMethod();
        System.out.println("try to stop name = " + Thread.currentThread().getName());
        printString.setContinuePrint(false);

        //打印停不下来，while循环一直在执行
    }
}
