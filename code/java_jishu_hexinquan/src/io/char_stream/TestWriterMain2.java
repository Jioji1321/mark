package io.char_stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class TestWriterMain2 {
	
	private static final String DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "io"
			+ File.separator + "fileDir" + File.separator;

	public static void main(String[] args) {
		
		File file = new File(DIR + "test3.txt");
		File file1 = new File(DIR + "test5.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//BufferedReader，缓冲流
		//使用InputStreamReader
		try (
			FileInputStream inStream1 = new FileInputStream(file);
			//获取字符输入流
			InputStreamReader inReader1 = new InputStreamReader(inStream1,"gbk");
			BufferedReader bufferedReader = new BufferedReader(inReader1);
			
			FileOutputStream outputStream = new FileOutputStream(file1);
			OutputStreamWriter outWriter = new OutputStreamWriter(outputStream);
			BufferedWriter bufferedWirter = new BufferedWriter(outWriter);
			){
				String line = null;
				while((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
					bufferedWirter.write(line);
					bufferedWirter.newLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
