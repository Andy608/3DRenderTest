package com.andy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.andy.shaders.ShaderProgram;

public class FileUtil {

	public static String loadFile(String fileName) {
		
		StringBuilder source = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(ShaderProgram.class.getClassLoader().getResourceAsStream(fileName)));
			String line;
			
			while ((line = reader.readLine()) != null) {
				source.append(line).append("\n");
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Error loading file: " + fileName);
			e.printStackTrace();
		}
		
		if (source != null) {
			return source.toString();
		}
		else {
			System.err.println("Invalid file path!");
			return null;
		}
	}
	
}
