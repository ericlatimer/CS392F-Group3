package JavaClasses;

import java.util.*;
import UML.*;

public class JavaGenerator
{
  protected static String nl;
  public static synchronized JavaGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    JavaGenerator result = new JavaGenerator();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = " ";
  protected final String TEXT_2 = NL + "   ";
  protected final String TEXT_3 = NL + "   " + NL + "      " + NL + "   package ";
  protected final String TEXT_4 = ";";
  protected final String TEXT_5 = NL + "   ";
  protected final String TEXT_6 = " ";
  protected final String TEXT_7 = " class ";
  protected final String TEXT_8 = "{" + NL + "   \t\t";
  protected final String TEXT_9 = NL + "   \t\t\t\t";
  protected final String TEXT_10 = " ";
  protected final String TEXT_11 = " ";
  protected final String TEXT_12 = " ";
  protected final String TEXT_13 = ";";
  protected final String TEXT_14 = NL + "   \t\tpublic ";
  protected final String TEXT_15 = "(){" + NL + "   \t\t}" + NL + "   \t\t" + NL + "   \t\tpublic ";
  protected final String TEXT_16 = "(";
  protected final String TEXT_17 = NL + "   \t\t\t";
  protected final String TEXT_18 = NL + "   \t\t";
  protected final String TEXT_19 = "){" + NL + "   \t\t\t   \t";
  protected final String TEXT_20 = "this.";
  protected final String TEXT_21 = " = ";
  protected final String TEXT_22 = ";";
  protected final String TEXT_23 = "\t\t" + NL + "   \t\t}" + NL + "   \t\t" + NL + "   \t\t";
  protected final String TEXT_24 = NL + "   \t\t\t";
  protected final String TEXT_25 = NL + "   \t\t";
  protected final String TEXT_26 = NL + "   \t\t" + NL + "   \t\t" + NL + "   \t\t";
  protected final String TEXT_27 = NL + "   \t\t";
  protected final String TEXT_28 = NL + "   \t\t\t";
  protected final String TEXT_29 = NL + "   \t\t";
  protected final String TEXT_30 = NL + "   \t\t" + NL + "   \t\t" + NL + "   }" + NL + "   " + NL + "   ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
     List list = (List)argument;
   List<Operation> operations = (List<Operation>)list.get(0);
   List<Attribute> attributes = (List<Attribute>)list.get(1);
   List<Parameter> parameters = (List<Parameter>)list.get(2);
   List<UMLClass> classes = (List<UMLClass>)list.get(3);
   List<UMLPackage> packages = (List<UMLPackage>)list.get(4);
   List<Namespace> namespaces = (List<Namespace>)list.get(5);
    stringBuffer.append(TEXT_2);
    for(Iterator<UMLClass> i = classes.iterator(); i.hasNext();){   
   UMLClass c = i.next();
   Boolean abs = c.isAbstract;
   String visibility = c.visibility;
   String modifier = " ";
   if(abs) modifier = "abstract";
   List<UMLPackage> pkgs = packages;
   List<Namespace> nspaces = namespaces;
   List<Operation> ops = operations;
   List<Parameter> params = parameters;
   List<Attribute> attrs = attributes;
   String pkgname = null;
   String fullname = null;
   UMLPackage pkg = null;
   for(Iterator<UMLPackage> p = pkgs.iterator(); p.hasNext();){
		UMLPackage temp = p.next();
   		if(temp.id == c.pid)
   			pkg = temp; 
   }
   pkgname = pkg.name;
   for(Iterator<Namespace> n = nspaces.iterator(); n.hasNext();){
   		Namespace temp = n.next();
   		if(temp.id == pkg.nsid)
   			fullname = temp.name + "." + pkgname;
   }
   
    stringBuffer.append(TEXT_3);
    stringBuffer.append(fullname);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(visibility);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(modifier);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(c.name);
    stringBuffer.append(TEXT_8);
    int cid = c.id;
   		Attribute atr;
   		List<String> typeList = new ArrayList<String>();
   		List<String> nameList = new ArrayList<String>();
   		List<String> getSetList = new ArrayList<String>();
   		for(Iterator<Attribute> a = attrs.iterator(); a.hasNext();){
   			atr = a.next();
   			if(cid == atr.ownerid){
   				String vis = atr.visibility;
   				String own = atr.ownerscope;
   				String type = atr.type;
   				String attrname = atr.name;
   				String get = "public " + type + " get" + attrname + "() {\n return this." + attrname + ";\n}";
   				String set = "public void set" + attrname + "(" + type + " " + attrname + ") {\n this." + attrname + " = " + attrname + ";\n}";
  				getSetList.add(get);
  				getSetList.add(set);
   				typeList.add(type);
   				nameList.add(attrname);
   				
   				
    stringBuffer.append(TEXT_9);
    stringBuffer.append(vis);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(own);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(type);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(attrname);
    stringBuffer.append(TEXT_13);
    
   			}   				
   		}
    stringBuffer.append(TEXT_14);
    stringBuffer.append(c.name);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(c.name);
    stringBuffer.append(TEXT_16);
    for(int u = 0; u < nameList.size(); u++){
   		String str =typeList.get(u) + " " + nameList.get(u); 
   		if(u < nameList.size()-1) str = str + ", ";
    stringBuffer.append(TEXT_17);
    stringBuffer.append(str);
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    for(int u = 0; u < nameList.size(); u++){
   					
    stringBuffer.append(TEXT_20);
    stringBuffer.append(nameList.get(u));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(nameList.get(u));
    stringBuffer.append(TEXT_22);
    
   		}
    stringBuffer.append(TEXT_23);
    for(int j=0; j < getSetList.size(); j++){
    stringBuffer.append(TEXT_24);
    stringBuffer.append(getSetList.get(j));
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    Operation op;
   		List<String> opList = new ArrayList<String>();
   		List<Parameter> ps = params;
   		for(Iterator<Operation> o = ops.iterator(); o.hasNext();){
   			op = o.next();
   			if(cid == op.ownerid){
   				String line = op.visibility + " " + op.ownerscope + " " + op.type + " " + op.name + "(";
   				Parameter param;
   				for(Iterator<Parameter> p = ps.iterator(); p.hasNext();){
   					param = p.next();
   					if(param.oid == op.id){
   						line = line + param.type + " " + param.name + ", ";
   					}
   				}
   				line = line.substring(0,(line.lastIndexOf(','))) + "){};";
   				opList.add(line);
   			}
   		}
    stringBuffer.append(TEXT_27);
    for(int t = 0; t < opList.size(); t++){
   			String str2 = opList.get(t); 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(str2);
    stringBuffer.append(TEXT_29);
    }
    stringBuffer.append(TEXT_30);
    }
    return stringBuffer.toString();
  }
}
