package generators;

import java.util.*;

public class TestGenerator
{
  protected static String nl;
  public static synchronized TestGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    TestGenerator result = new TestGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "import java.util.*;" + NL + "import generators.";
  protected final String TEXT_2 = ";" + NL + "public class Test{" + NL + "\tpublic static void main(String[] args){" + NL + "\t  List<Object> objs =(new ";
  protected final String TEXT_3 = "()).initialize();" + NL + "\t  ";
  protected final String TEXT_4 = " gen = new ";
  protected final String TEXT_5 = "();" + NL + "\t  System.out.print(gen.generate(objs));" + NL + "\t}" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    List<String> classNames = (List<String>)argument;
  String initializeClassName = classNames.get(0);
  String genName = classNames.get(classNames.size() - 1);
    stringBuffer.append(TEXT_1);
    stringBuffer.append(genName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(initializeClassName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genName);
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
