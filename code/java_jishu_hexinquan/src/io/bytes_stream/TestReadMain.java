package io.bytes_stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestReadMain {

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

		// 字节流和字符流
		// 对于字节流，他是用来操作二进制文件的，因为字节流可以操作的数据是8位的，也就是1字节，像数字和字母就是1个字节的，但是汉字就不能用字节流来操作
		// 因为一个汉字是两个字节，就是16位，字符流可以操作16位，所以文本文件则常用字符流进行操作

		// FileInputStream和FileOutputStream
		// 读取文本
		try (FileInputStream in = new FileInputStream(file1); FileInputStream in1 = new FileInputStream(file1);) {
			// FileInputStream in = new FileInputStream(file1);

			// 一个一个字节的读取，效率慢
			int i = in.read();
			while (i > 0) { // 当获取到的i为-1时，则表示已经读到末尾
				System.out.println((char) i);
				i = in.read(); // 每调用一次read()方法就会获取到下一个字节，int类型实际就是字符的ASCII码值，转换为char类型
			}

			// 以byte数组的形式读取，速度会快一些
			byte[] bytes = new byte[1024]; // 以1024个字节也就是1kb的方式去读取，如果文件大小不超过1kb就会全部读取，将读取到的内容放在byte数组中并返回文本内容的字节数
//			FileInputStream in1 = new FileInputStream(file1);
			int i1 = in1.read(bytes);
			String s = new String(bytes);
			System.out.println(s);
			System.out.println(i1);

//			in1.close();
//			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
