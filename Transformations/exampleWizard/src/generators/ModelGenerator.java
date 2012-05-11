package generators;

import java.util.*;
import java.util.regex.*;

public class ModelGenerator
{
  protected static String nl;
  public static synchronized ModelGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    ModelGenerator result = new ModelGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "  " + NL + "public class ";
  protected final String TEXT_2 = "{";
  protected final String TEXT_3 = NL + "    public ";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = ";";
  protected final String TEXT_6 = NL + "  public ";
  protected final String TEXT_7 = "(";
  protected final String TEXT_8 = "){";
  protected final String TEXT_9 = NL + "      this.";
  protected final String TEXT_10 = " = ";
  protected final String TEXT_11 = ";";
  protected final String TEXT_12 = NL + "  }" + NL + "}" + NL + "/// end of a file ///   ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    Map<String, List<String>> factMap = (Map<String, List<String>>)argument;
  List<String> classNames = new ArrayList<String>(factMap.keySet());
  Pattern digitPattern = Pattern.compile("[0-9]+");
  Pattern booleanPattern = Pattern.compile("true|false|False|True");
  Pattern literalPattern = Pattern.compile("\'.*\'");
  for(String className : classNames){
    List<String> facts = factMap.get(className);
    String schema = facts.get(0);
    String[] data = schema.split(",");
    for (int i = 0; i < data.length; ++i)
    	data[i] = data[i].trim();
    String fact = facts.get(1);
    String[] data2 = fact.split(",");
    for (int i = 0; i < data2.length; ++i)
    	data2[i] = data2[i].trim(); 
    	  
	 //Eric's fix in case tmp doesn't have ( and/or )
	 int start = 0;
	 int end = data2[data2.length - 1].length();
	 if (data2[0].indexOf("(") > -1)
	 	start = data2[0].indexOf("(") + 1;
	  if (data2[data2.length - 1].indexOf(")") > -1)
	 	end = data2[data2.length - 1].indexOf(")");        
	     
    data2[0] = data2[0].substring(start);
    data2[data2.length - 1] = data2[data2.length - 1].substring(0, end);
    List<String> vars = new ArrayList<String>();
    for(int i = 1; i < data.length; i++){
      vars.add(data[i]);
    }
    List<String> values = new ArrayList<String>();
    for(int i = 0; i < data2.length; i++){
      values.add(data2[i]);
    }
    String value = null;
    String type = null;
    List<String> types = new ArrayList<String>();
    for(int i = 0; i < values.size(); i++){
      value = values.get(i);
      if(digitPattern.matcher(value).matches()){
        type = "Integer";  
      }else if(booleanPattern.matcher(value).matches()){
        type = "boolean";
      }else{
        type = "String";
      }
      types.add(type);
  }
    stringBuffer.append(TEXT_1);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_2);
    StringBuffer buffer = new StringBuffer();
    for(int i = 0; i < vars.size(); i++){
    buffer.append(types.get(i)).append(" ").append(vars.get(i)).append(", ");
    stringBuffer.append(TEXT_3);
    stringBuffer.append(types.get(i));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(vars.get(i));
    stringBuffer.append(TEXT_5);
    }buffer.setLength(buffer.length() - 2);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(buffer.toString());
    stringBuffer.append(TEXT_8);
    for(int i = 0; i < vars.size(); i++){
    stringBuffer.append(TEXT_9);
    stringBuffer.append(vars.get(i));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(vars.get(i));
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    }
    return stringBuffer.toString();
  }
}
