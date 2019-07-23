package static_jingtai;

public class TestMain {

	public static void main(String[] args) {
		Employee e1 = new Employee();
		e1.setId();
		System.out.println(e1.getId()); //0
		
		Employee e2 = new Employee();
		e2.setId();
		System.out.println(e2.getId()); //1
	}
}

class Employee{
	private static int index = 0;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId() {
		id = index;
		index++;
	}
	
}


