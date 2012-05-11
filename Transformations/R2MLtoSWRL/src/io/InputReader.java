package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import SWRL.*;

public class InputReader {

	private List<Atom> atoms = null;
	private List<Body> bodies = null;
	private List<Head> heads = null;
	private List<Implies> implies = null;
	private List<Variable> vars = null; 
	
	public List<Object> read(String fileName) {		
		atoms = new ArrayList<Atom>();
		bodies = new ArrayList<Body>();
		heads = new ArrayList<Head>();
		implies = new ArrayList<Implies>();
		vars = new ArrayList<Variable>();
		
		List<Object> list = new ArrayList<Object>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("%") || line.equals("")) {
					continue;
				}
				if (line.startsWith("atom")) {
					Atom a = strToAtom(line.substring(
						line.indexOf("(") + 1, line.indexOf(")")));
					atoms.add(a);
				}				
				else if (line.startsWith("body")) {
					Body b = strToBody(
						line.substring(line.indexOf("(") + 1,line.indexOf(")")));
					bodies.add(b);
				}
				else if (line.startsWith("head")) {
					Head h = strToHead(
						line.substring(line.indexOf("(") + 1,line.indexOf(")")));
					heads.add(h);
				}
				else if (line.startsWith("implies")) {
					Implies i = strToImplies(
						line.substring(line.indexOf("(") + 1,line.indexOf(")")));
					implies.add(i);
				}
				else if (line.startsWith("variables")) {
					Variable v = strToVar(
						line.substring(line.indexOf("(") + 1,line.indexOf(")")));
					vars.add(v);
				}
				else
						continue;
			}
			
			list.add(atoms);
			list.add(bodies);
			list.add(heads);
			list.add(implies);
			list.add(vars);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private Atom strToAtom(String str) {
		String[] data = str.split(",");
		return new Atom(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim());
	}

	private Body strToBody(String str) {
		String[] data = str.split(",");
		return new Body(data[0].trim(), data[1].trim());
	}
	
	private Head strToHead(String str) {
		String[] data = str.split(",");
		return new Head(data[0].trim(), data[1].trim());
	}
	private Implies strToImplies(String str) {
		String[] data = str.split(",");
		return new Implies(data[0].trim(), data[1].trim());
	}
	private Variable strToVar(String str) {
		String[] data = str.split(",");
		return new Variable(data[0].trim(), data[1].trim());
	}

	
}
