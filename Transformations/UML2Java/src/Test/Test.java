package Test;

import JavaClasses.JavaGenerator;
import io.InputReader;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		InputReader reader = new InputReader();
		List<Object> list = reader.read("UML2Java.pl");
		System.out.println((new JavaGenerator()).generate(list));
	}
}
