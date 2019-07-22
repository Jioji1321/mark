package io.char_stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class TestReadStream {
	
	private static final String DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "io"
			+ File.separator + "fileDir" + File.separator;

	public static void main(String[] args) {

		//BufferedReader,FileReader,InputStreamReader
		File file = new File(DIR + "test3.txt");
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			//使用InputStreamReader
			FileInputStream inStream  = new FileInputStream(file);
			//获取字符输入流
			InputStreamReader inReader = new InputStreamReader(inStream,"gbk");
			
			//使用FileReader
			//FileReader不需要传入一个字节流，但是不能够指定字符集
			FileReader fileReader = new FileReader(file);
			
			char[] c = new char[64];
			
			//使用InputStreamReader
			//int read = inReader.read(c);
			
			//使用FileReader
			int reader = fileReader.read(c);
			
			System.out.println(new String(c));
			
			
			//BufferedReader，缓冲流
			//使用InputStreamReader
			FileInputStream inStream1  = new FileInputStream(file);
			//获取字符输入流
			InputStreamReader inReader1 = new InputStreamReader(inStream1,"gbk");
			BufferedReader bufferedReader = new BufferedReader(inReader1);
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			
			bufferedReader.close();
			inReader.close();
			inStream.close();
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
