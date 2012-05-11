package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import UML.*;

public class InputReader {

	private List<Operation> operations = null;
	private List<Attribute> attributes = null;
	private List<Parameter> parameters = null;
	private List<UMLClass> umlclasses = null;
	private List<Namespace> namespaces = null;
	private List<UMLPackage> packages = null;
	
	public List<Object> read(String fileName) {		
		operations = new ArrayList<Operation>();
		attributes = new ArrayList<Attribute>();
		parameters = new ArrayList<Parameter>();
		umlclasses = new ArrayList<UMLClass>();
		namespaces = new ArrayList<Namespace>();
		packages = new ArrayList<UMLPackage>();
		
		List<Object> list = new ArrayList<Object>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("%") || line.equals("")) {
					continue;
				}
				if (line.startsWith("operation")) {
					Operation o = strToOperation(line.substring(
						line.indexOf("(") + 1, line.indexOf(")")));
					operations.add(o);
				}				
				else if (line.startsWith("attribute")) {
					Attribute a = strToAttribute(
						line.substring(line.indexOf("(") + 1,line.indexOf(")")));
					attributes.add(a);
				}
				else if (line.startsWith("parameter")) {
					Parameter p = strToParameter(line.substring(
						line.indexOf("(") + 1, line.indexOf(")")));
					parameters.add(p);
				}
				else if (line.startsWith("class")) {
					UMLClass u = strToUMLClass(line.substring(
						line.indexOf("(") + 1, line.indexOf(")")));
					umlclasses.add(u);
				}
				else if (line.startsWith("package")) {
					UMLPackage p = strToPackage(line.substring(
						line.indexOf("(") + 1, line.indexOf(")")));
					packages.add(p);
				}
				else if (line.startsWith("namespace")) {
					Namespace n = strToNamespace(line.substring(
						line.indexOf("(") + 1, line.indexOf(")")));
					namespaces.add(n);
				}
			}

			list.add(operations);
			list.add(attributes);
			list.add(parameters);
			list.add(umlclasses);
			list.add(packages);
			list.add(namespaces);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private Operation strToOperation(String str) {
		String[] data = str.split(",");
		return new Operation(Integer.valueOf(data[0].trim()), data[1].trim(),data[2].trim(),
				Integer.valueOf(data[3].trim()), data[4].trim(), data[5].trim(), Integer.valueOf(data[6].trim()));
	}

	private Attribute strToAttribute(String str) {
		String[] data = str.split(",");
		return new Attribute(Integer.valueOf(data[0].trim()), data[1].trim(), data[2].trim(),Integer.valueOf(data[3].trim()),data[4].trim(), data[5].trim(),Boolean.valueOf(data[6].trim()),Integer.valueOf(data[7].trim()));
	}
	
	private Parameter strToParameter(String str) {
		String[] data = str.split(",");
		return new Parameter(Integer.valueOf(data[0].trim()), 
				data[1].trim(), data[2].trim(), Integer.valueOf(data[3].trim()));
	}
	
	private UMLClass strToUMLClass(String str) {
		String[] data = str.split(",");
		return new UMLClass(Integer.valueOf(data[0].trim()), 
				data[1].trim(),data[2].trim(), Integer.valueOf(data[3].trim()),Boolean.valueOf(data[4].trim()));	
	}
	
	private DataType strToDataType(String str) {
		String[] data = str.split(",");
		return new DataType(Integer.valueOf(data[0].trim()), 
				data[1].trim(), Integer.valueOf(data[2].trim()));	
	}
	
	private UMLPackage strToPackage(String str) {
		String[] data = str.split(",");
		return new UMLPackage(Integer.valueOf(data[0].trim()), 
				data[1].trim(), Integer.valueOf(data[2].trim()));	
	}
	private Namespace strToNamespace(String str) {
		String[] data = str.split(",");
		return new Namespace(Integer.valueOf(data[0].trim()), 
				data[1].trim());	
	}
	
}
