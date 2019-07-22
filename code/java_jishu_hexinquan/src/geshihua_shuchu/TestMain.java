package geshihua_shuchu;

public class TestMain {

	public static void main(String[] args) {
		
		Double doubleNum = 300000000.00 / 15000000.00;
		System.out.printf("%2.4f",doubleNum); // 2.4f，小数点前保留两位，不足的位数用空格补足；小数点后保留4位小数，f转换为定点浮点数
	}

}
