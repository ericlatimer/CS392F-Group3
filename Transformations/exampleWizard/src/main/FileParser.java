package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileParser {

	private String fileName = null;

	public FileParser(String fileName) {
		this.fileName = fileName;
	}

	public Map<String, List<String>> parse() {
		Map<String, List<String>> factMap = new HashMap<String, List<String>>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			String line = null;
			String[] data = null;
			String tmp = null;
			String className = null;
			List<String> facts = null;
			line = br.readLine();
			while (line != null) {
				if (line.startsWith("% schema:") ||
					line.startsWith("%schema:")) {
					data = line.split(",");
					tmp = data[0];
					tmp = tmp.substring(tmp.indexOf(":") + 1).trim();
					className = tmp.substring(0, 1).toUpperCase()
							+ tmp.substring(1);
					facts = new ArrayList<String>();
					do {
						facts.add(line);
					} while ((line = br.readLine()) != null && !line.isEmpty());
					factMap.put(className, facts);
					if (line == null)
						break;
					else
						continue;
				}
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return factMap;
	}
}
