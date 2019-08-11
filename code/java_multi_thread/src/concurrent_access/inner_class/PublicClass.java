package concurrent_access.inner_class;

/**
 * @ClassName PublicClass
 * @Description TODO 内置类和静态内置类
 * @Author jioji
 * @Date 2019/08/09 0009 14:03
 * @Version 1.0
 **/
public class PublicClass {
    private String username;
    private String password;

    class PrivateClass {
        private String age;
        private String address;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void printPublicProperty() {
            System.out.println(username + " " + password);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
