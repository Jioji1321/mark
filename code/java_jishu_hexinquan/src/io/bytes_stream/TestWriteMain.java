package io.bytes_stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestWriteMain {

	private static final String DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "io"
			+ File.separator + "fileDir" + File.separator;
	
	public static void main(String[] args) {
		
		//写入
		try {
			byte[] bytes = new byte[1024];
			FileOutputStream out = new FileOutputStream(DIR + "test2.txt",true); //第二个参数：true为追加，false为覆盖
			out.write(bytes);
			
			
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
