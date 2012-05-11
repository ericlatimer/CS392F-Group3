package generators;

import java.util.*;
import java.util.regex.*;

public class InitializerGenerator
{
  protected static String nl;
  public static synchronized InitializerGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    InitializerGenerator result = new InitializerGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "import model.";
  protected final String TEXT_3 = ";   ";
  protected final String TEXT_4 = NL + "import java.util.*;   " + NL + "   " + NL + "public class Initializer{";
  protected final String TEXT_5 = NL + "  private List<";
  protected final String TEXT_6 = "> ";
  protected final String TEXT_7 = " = null;";
  protected final String TEXT_8 = NL + "  " + NL + "  public List<Object> initialize(){" + NL + "     List<Object> result = new ArrayList<Object>();" + NL + "  \t ";
  protected final String TEXT_9 = "   ";
  protected final String TEXT_10 = NL + "     ";
  protected final String TEXT_11 = " = new ArrayList<";
  protected final String TEXT_12 = ">();";
  protected final String TEXT_13 = NL + "     ";
  protected final String TEXT_14 = " ";
  protected final String TEXT_15 = "=null;";
  protected final String TEXT_16 = NL + "         ";
  protected final String TEXT_17 = NL + "     ";
  protected final String TEXT_18 = " = new ";
  protected final String TEXT_19 = "(";
  protected final String TEXT_20 = ");";
  protected final String TEXT_21 = NL + "     ";
  protected final String TEXT_22 = ".add(";
  protected final String TEXT_23 = ");";
  protected final String TEXT_24 = NL + "     result.add(";
  protected final String TEXT_25 = ");\t" + NL + "  \t ";
  protected final String TEXT_26 = NL + "     return result;" + NL + "  }" + NL + "}   ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
     Map<String, List<String>>factMap = (Map<String, List<String>>)argument;
   Set<String> reservedWords = new HashSet<String>();
   reservedWords.add("public");
   reservedWords.add("private");
   reservedWords.add("protected");
   List<String> classNames = new ArrayList<String>(factMap.keySet());
   Pattern literalPattern = Pattern.compile("'.*'");
   Pattern digitPattern = Pattern.compile("[0-9]+");
   Pattern booleanPattern = Pattern.compile("true|false");
   for(String className : classNames){
    stringBuffer.append(TEXT_2);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_3);
    }
    stringBuffer.append(TEXT_4);
     List<String> instNames = new ArrayList<String>();
   List<String> listNames = new ArrayList<String>();
   String instName = null;
   String listName = null;
   String tmp = null;
   String[] data = null;
   for(String className : classNames){
     instName = className.substring(0, 1).toLowerCase() + className.substring(1) + "Inst";
     instNames.add(instName);
     listName = instName + "List";
     listNames.add(listName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(listName);
    stringBuffer.append(TEXT_7);
    }
    stringBuffer.append(TEXT_8);
    String strArgu = null;
  	   String className = null;
  	   for(int i = 0; i < classNames.size(); i++){
  		 listName = listNames.get(i);
  	     instName = instNames.get(i);
         className = classNames.get(i);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(listNames.get(i));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(classNames.get(i));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(instName);
    stringBuffer.append(TEXT_15);
    List<String> facts = factMap.get(classNames.get(i));
       for(int j = 1; j < facts.size(); j++){
         tmp = facts.get(j);

         //Eric's fix in case tmp doesn't have ( and/or )
         int start = 0;
         int end = tmp.length();
         if (tmp.indexOf("(") > -1)
         	start = tmp.indexOf("(") + 1;
          if (tmp.indexOf(")") > -1)
         	end = tmp.indexOf(")");        
         strArgu = tmp.substring(start, end);
         data = strArgu.split(",");
         
         StringBuffer buffer = new StringBuffer();
         for(int k = 0; k < data.length; k++){
           String curData = data[k].trim();
           if(literalPattern.matcher(curData).matches()){
             curData = "\"" + curData.substring(1, curData.length() -1) + "\"";
           }else if(booleanPattern.matcher(curData).matches()){
           }else if(digitPattern.matcher(curData).matches()){
           }
           else{
             curData = "\"" + curData + "\"";
           }
           buffer.append(curData).append(",");
         }buffer.setLength(buffer.length() - 1);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(instName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(buffer.toString());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(listName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(instName);
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    stringBuffer.append(listName);
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    return stringBuffer.toString();
  }
}
