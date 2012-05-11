package main;

import generators.InitializerGenerator;
import generators.ModelGenerator;
import generators.TestGenerator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		FileParser parser = new FileParser(args[0]);
		Map<String, List<String>> factMap = parser.parse();
		// System.out.println((new InitializerGenerator()).generate(factMap));
		FileCreator fc = new FileCreator(args[0]);
//	    Iterator it = factMap.entrySet().iterator();
//	    while (it.hasNext()) {
//	        Map.Entry pairs = (Map.Entry)it.next();
//	        System.out.println(pairs.getKey() + " = " + pairs.getValue());
//	        it.remove(); // avoids a ConcurrentModificationException
//	    }
		// System.out.print(new ModelGenerator().generate(factMap));
		List<String> classNames = fc.createFile(FileCreator.mainPack,
				(new InitializerGenerator()).generate(factMap));
		List<String> modelClassNames = fc.createFile(FileCreator.modelPack,
				(new ModelGenerator()).generate(factMap));
		fc.createTemplateFile(modelClassNames);
		classNames.add(fc.genName);
		fc.createFile(FileCreator.mainPack,
				(new TestGenerator()).generate(classNames));
		System.out.println("!! Done !!");
		// Pattern pat = Pattern.compile("'.*'");
		// System.out.print(pat.matcher("'String'").matches();
		// System.out.println("Successful!");
		// fc.convertToJET();
	}
}
