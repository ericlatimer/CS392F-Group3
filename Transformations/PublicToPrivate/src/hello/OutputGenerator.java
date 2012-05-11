package hello;

import java.util.*;
import model.Attribute;
import model.Class;
import model.DataType;

public class OutputGenerator
{
  protected static String nl;
  public static synchronized OutputGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    OutputGenerator result = new OutputGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "import java.util.*;   " + NL + "import model.Attribute;" + NL + "import model.Class;" + NL + "import model.DataType;   ";
  protected final String TEXT_2 = " ";
  protected final String TEXT_3 = " class ";
  protected final String TEXT_4 = " {";
  protected final String TEXT_5 = " ";
  protected final String TEXT_6 = "; ";
  protected final String TEXT_7 = NL + "     public void set";
  protected final String TEXT_8 = "(";
  protected final String TEXT_9 = " ";
  protected final String TEXT_10 = "){" + NL + "       this.";
  protected final String TEXT_11 = " = ";
  protected final String TEXT_12 = ";" + NL + "     }         " + NL + "     public ";
  protected final String TEXT_13 = " get";
  protected final String TEXT_14 = "(){" + NL + "       return this.";
  protected final String TEXT_15 = ";" + NL + "     }" + NL + "           }else{%>";
  protected final String TEXT_16 = NL + "     ";
  protected final String TEXT_17 = " ";
  protected final String TEXT_18 = " ";
  protected final String TEXT_19 = ";";
  protected final String TEXT_20 = NL + "   }" + NL + "}%>";
  protected final String TEXT_21 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     List<Object> objs = (List<Object>)argument; 
   List<Attribute> attrs = (List<Attribute>)objs[0];
   List<Class> classes = (List<Class>)objs[1];
   List<DataType> dts = (List<DataType>)obj[2];
    stringBuffer.append(TEXT_1);
    Class clas = null;
  for(int i = 0; i < classes.size(); i++){
	clas = classes.get(i);
	

    stringBuffer.append(TEXT_2);
    stringBuffer.append(clas.visibility);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(clas.className);
    stringBuffer.append(TEXT_4);
    String classID = clas.classID;
       Attribute attr = null;
       DataType dt = null;
       for(int j = 0; j < attrs.size(); j++){
         attr = attrs.get(j);
         if(attr.classID.equals(classID){
           for(int k = 0; k < dts.size(); k++){
             if(dts.get(k).dataTypeID.equals(attr.dataTypeID){
                dt = dts.get(k);
                break;
             }
           }
           if(attr.visibility.equals("public")){
     private <%=dt.name
    stringBuffer.append(TEXT_5);
    stringBuffer.append(attr.attrName);
    stringBuffer.append(TEXT_6);
    
       String newName = attr.attrName;
       newName = newName.charAt(0).toUpperCase() + newName.substring(1);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(newName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(dt.name);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(attr.attrName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(attr.attrName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(attr.attrName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(dt.name);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(newName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(attr.attrName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(attr.visibility);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(dt.name);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(attr.attName);
    stringBuffer.append(TEXT_19);
    }
           }
         }  
     }
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
