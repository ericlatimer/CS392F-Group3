package test;

import fruitGenerator.*;
import io.InputReader;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		InputReader reader = new InputReader();
		List<Object> list = reader.read("FruitInput.prolog");
		System.out.println((new FruitGenerator()).generate(list));
		//System.out.println((new FruitGeneratorInstance()).generate(list));
	}
}