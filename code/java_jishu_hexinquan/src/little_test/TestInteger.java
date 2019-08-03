package little_test;


//https://blog.csdn.net/renyajie2345/article/details/71515610
//https://blog.csdn.net/hebsun/article/details/83533385

public class TestInteger {

	public static void main(String[] args) {
		Integer id1 = 120;
		Integer id2 = 120;
		
		
		if (id1 == id2) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		
		
		if (id1.equals(id2)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

		
	}

}
