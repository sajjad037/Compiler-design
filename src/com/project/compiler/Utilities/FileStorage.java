package com.project.compiler.Utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is for saving a map
 * 
 * @author SajjadAshrafCan
 * 
 */
public class FileStorage {
	/**
	 * 
	 * Save file takes input file path and fileContent as string
	 * 
	 * @param path
	 * @param fileContent
	 * @return
	 */
	public String saveTxtFile(String path, String fileContent) {
		String status = "";
		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(fileContent);
			fileWriter.flush();
			fileWriter.close();
			status = "OK";
		} catch (Exception e) {
			e.printStackTrace();
			status = e.getMessage();
		}
		return status;
	}

	/**
	 * Read text file from path
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String readTxtFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
