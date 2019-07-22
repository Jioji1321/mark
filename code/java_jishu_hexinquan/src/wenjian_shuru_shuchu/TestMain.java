package wenjian_shuru_shuchu;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

public class TestMain {

	private final static String FILE_DIRECTORY = "D:\\Java_workspace\\java_jishu_hexinquan\\src\\wenjian_shuru_shuchu\\testDir\\";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		//获取当前根目录， D:\Java_workspace\java_jishu_hexinquan
		String location = System.getProperty("user.dir");
		System.out.println(location);
		
		// 读取方法
		Scanner in = new Scanner(
				Paths.get(FILE_DIRECTORY + "testRead.txt"),
				"UTF-8");
		if (in.hasNext()) {
			System.out.println(in.next());
		}

		// 写入方法
		File dir = new File(FILE_DIRECTORY);
		//判断目录是否存在，不存在则创建
		if(!(dir.exists())) {
			dir.mkdirs();
		}
		PrintWriter out = new PrintWriter(FILE_DIRECTORY + "testOut.txt","UTF-8");
		//out.append("测试写入文件：12345");
		out.println("这个方法是怎么使用的呢？");
		out.println("这个方法是怎么使用的呢？");
		
		in.close();
		out.close();
	}

}
