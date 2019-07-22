package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestIOMain {

	private static final String DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "io"
			+ File.separator + "fileDir" + File.separator;

	public static void main(String[] args) {

		File file1 = new File(DIR + "test1.txt");

		if (!file1.exists()) {
			try {
				file1.createNewFile(); // 新建文件
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// mkdir()和mkdirs()的区别：
		// mkdirs()可以创建多级嵌套目录
		File file2 = new File(DIR + "testDir1" + File.separator + "testDir2");
		boolean flag1 = file2.mkdir(); // false
		boolean flag2 = file2.mkdirs(); // true
		System.out.println(flag1 + " " + flag2);

	}

}
