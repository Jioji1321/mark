package concurrent_access.inner_class;

/**
 * @ClassName Run
 * @Description TODO
 * @Author jioji
 * @Date 2019/08/09 0009 14:06
 * @Version 1.0
 **/
public class Run {

    public static void main(String[] args) {
        PublicClass publicClass = new PublicClass();
        publicClass.setUsername("xixi");
        publicClass.setPassword("haha");
        System.out.println(publicClass.getUsername() + ", " + publicClass.getPassword());


        // 声明内部类对象需要先声明外部类类对象之后再用外部类类对象去声明内部类类对象
        PublicClass.PrivateClass privateClass = publicClass.new PrivateClass();
        privateClass.setAge("ageValue");
        privateClass.setAddress("addressValue");
        System.out.println(privateClass.getAge() + ", " + privateClass.getAddress());
    }


}
