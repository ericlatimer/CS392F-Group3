package fruitGenerator;

import java.util.*;
import fruit.*;

public class FruitGenerator
{
  protected static String nl;
  public static synchronized FruitGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    FruitGenerator result = new FruitGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    " + NL + "class ";
  protected final String TEXT_2 = " {" + NL + "    int id = ";
  protected final String TEXT_3 = ";" + NL + "    \t" + NL + "    public int getId() {" + NL + "    \treturn id;" + NL + "    }" + NL + "    \t" + NL + "    public String getName() {" + NL + "    \treturn \"";
  protected final String TEXT_4 = "\";" + NL + "    }" + NL + "}" + NL;
  protected final String TEXT_5 = " ";
  protected final String TEXT_6 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     List list = (List)argument; 
   List<Fruit> fruits = (List<Fruit>)list.get(0);
    
Iterator<Fruit> it = fruits.iterator();
while (it.hasNext()) {	
	Fruit fruit 		= it.next();
	String fruitId 		= fruit.getId(); 
    String fruitName 	= fruit.getName();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(fruitName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(fruitId);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(fruitName);
    stringBuffer.append(TEXT_4);
    
}

    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}
