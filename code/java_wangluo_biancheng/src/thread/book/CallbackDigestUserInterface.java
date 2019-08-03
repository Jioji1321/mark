package thread.book;
/**
 * Created by jioji on 2019/07/31 0031.
 */

import javax.xml.bind.DatatypeConverter;

/**
 * @ClassName CallbackDigestUserInterface
 * @Description TODO
 * @Author jioji
 * @Date 2019/07/31 0031 20:30
 * @Version 1.0
 **/
public class CallbackDigestUserInterface {

    public static void receiceDigest(byte[] digest, String name){
        StringBuilder result = new StringBuilder(name);
        result.append(": ");
        result.append(DatatypeConverter.printHexBinary(digest));
        System.out.println(result);
    }

    public static void main(String[] args){
        for(String filename: args){
            //计算摘要
            CallbackDigest cb = new CallbackDigest(filename);
            Thread t = new Thread(cb);
            t.start();
        }
    }
}
