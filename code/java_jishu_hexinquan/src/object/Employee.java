package object;

import java.util.Date;

public class Employee {

	private Date hireDay;

	public Date getHireDay() {
		//return hireDay;
		return (Date) hireDay.clone();
	}

	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	
	public Employee(Date hireDay) {
		this.hireDay = hireDay;
	}

	public static void main(String[] args) {
		Employee harry = new Employee(new Date());
		Date d = harry.getHireDay();
		double tenYearsInMilliSeconds = 10 * 365 * 24 * 60 * 60 * 1000;
		d.setTime(d.getTime() - (long)tenYearsInMilliSeconds); // java.lang.NullPointerException
		
		//System.out.println();
		/**
		 * 出错原因：
		 * d和harry.getHireday()引用的是同一个Date对象，对d调用更改器（setter方法）就可以自动改变这个Employee对象的私有化
		 */
	}
}
