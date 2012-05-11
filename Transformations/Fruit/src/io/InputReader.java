package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fruit.*;

public class InputReader {

	private List<Fruit> fruits = null;
		
	public List<Object> read(String fileName) {		
		fruits = new ArrayList<Fruit>();
		
		List<Object> list = new ArrayList<Object>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("%") || line.equals("")) {
					continue;
				}
				if (line.startsWith("fruit")) {
					addFruit(line.substring(
						line.indexOf("(") + 1, line.indexOf(")")));
				}				
			}

			list.add(fruits);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private void addFruit(String str) {
		String[] data = str.split(",");
		String name = data[1].trim().replace("'", "");
		fruits.add(new Fruit(data[0].trim(), name));
	}	
}