package test;

import io.InputReader;

import java.util.List;

import SWRLOut.JavaGenerator;

public class Test {
	public static void main(String[] args) {
		InputReader reader = new InputReader();
		List<Object> list = reader.read("SWRLinput.pl");
		System.out.println((new JavaGenerator()).generate(list));
	}

}
