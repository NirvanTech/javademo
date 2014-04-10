/**
 * 
 */
package com.demo.streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author parupati
 *
 */
public class FileScanner {
	public static void readFileChunks(String file, ScannerListner listner)
			throws IOException, FileNotFoundException {
		readFileChunks(file,null, listner);
	}

	public static void readFileChunks(String file, String pattern, ScannerListner listner)
			throws IOException, FileNotFoundException {
		InputStream stream = FileScanner.class.getResourceAsStream(file);
		if(stream == null)
			stream = new FileInputStream(new File(file));
		if(stream == null)
			throw new FileNotFoundException(file);
		Scanner scanner = new Scanner(stream);
		if(pattern != null){
			scanner.useDelimiter(pattern);
			while(scanner.hasNext()){
				String line = scanner.next();
				if(listner!=null)listner.onLineEnd(line);
			}
		}else{
			while(scanner.hasNext()){
				String line = scanner.nextLine();
				if(listner!=null) listner.onLineEnd(line);
			}
		}
	}

	public static void main(String[] args) {
		try {
			readFileChunks("F:\\test-cases\\1000newproje.txt", null, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
