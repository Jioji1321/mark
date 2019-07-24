package abstract_class;

public class TestMain {

	public static void main(String[] args) {
		
	}

}

abstract class Student{
	public abstract String getDescription();
	
	private String name;

	public Student(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

class Xiaoming extends Student{

	public Xiaoming(String name) {
		super(name);
	}

	@Override
	public String getDescription() {
		return null;
	}
	
}