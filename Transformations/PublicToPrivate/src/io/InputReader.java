package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Attribute;
import model.Class;
import model.DataType;

public class InputReader {
	private List<Class> classes = null;
	private List<Attribute> attrs = null;
	private List<DataType> dts = null;

	public List<Object> read(String fileName) {
		List<Object> result = new ArrayList<Object>();
		classes = new ArrayList<Class>();
		attrs = new ArrayList<Attribute>();
		dts = new ArrayList<DataType>();
		String[] data = null;
		String title = null;
		String authors = null;
		int numPages = -1;
		Class clas = null;
		Attribute attr = null;
		DataType dt = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.isEmpty())
					continue;
				if (line.startsWith("attribute")) {
					line = line.substring(line.indexOf('(') + 1);
					line = line.substring(0, line.indexOf(')'));
					data = line.split(", ");
					attr = new Attribute(data[0], data[1], data[2], data[3],
							Boolean.valueOf(data[4]), data[5], data[6], data[7]);
					attrs.add(attr);
				} else if (line.startsWith("class")) {
					line = line.substring(line.indexOf('(') + 1);
					line = line.substring(0, line.indexOf(')') + 1);
					data = line.split(", ");
					clas = new Class(data[0], data[1].substring(1,
							data[1].length() - 2), data[2],
							Boolean.valueOf(data[3]), Boolean.valueOf(data[4]),
							Boolean.valueOf(data[5]), Boolean.valueOf(data[6]),
							Boolean.valueOf(data[7]));
					classes.add(clas);
				} else if (line.startsWith("dataType")) {
					line = line.substring(line.indexOf('(') + 1);
					line = line.substring(0, line.indexOf(')') + 1);
					data = line.split(", ");
					dt = new DataType(data[0], data[1].substring(1,
							data[1].length() - 2), Boolean.valueOf(data[2]),
							Boolean.valueOf(data[3]), Boolean.valueOf(data[4]),
							Boolean.valueOf(data[5]));
					dts.add(dt);
				}
			}
			result.add(attrs);
			result.add(classes);
			result.add(dts);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
