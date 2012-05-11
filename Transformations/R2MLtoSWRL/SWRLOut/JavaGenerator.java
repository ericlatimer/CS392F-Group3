package SWRLOut;

import java.util.*;
import SWRL.*;

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
  protected final String TEXT_1 = "  Rules:";
  protected final String TEXT_2 = NL + " ";
  protected final String TEXT_3 = ". ";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = " ";
  protected final String TEXT_6 = " ";
  protected final String TEXT_7 = NL + "\t\t";
  protected final String TEXT_8 = ", ";
  protected final String TEXT_9 = "and ";
  protected final String TEXT_10 = ", therefore";
  protected final String TEXT_11 = " ";
  protected final String TEXT_12 = " ";
  protected final String TEXT_13 = " ";
  protected final String TEXT_14 = " ";
  protected final String TEXT_15 = NL + "\t\t";
  protected final String TEXT_16 = ", ";
  protected final String TEXT_17 = "and ";
  protected final String TEXT_18 = ".";
  protected final String TEXT_19 = NL + "   " + NL + "   ";
  protected final String TEXT_20 = NL + "   ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     List list = (List)argument;
   List<Atom> atoms = (List<Atom>)list.get(0);
   List<Body> bodies = (List<Body>)list.get(1);
   List<Head> heads = (List<Head>)list.get(2);
   List<Implies> implies = (List<Implies>)list.get(3);
   List<Variable> vars = (List<Variable>)list.get(4);
   
    stringBuffer.append(TEXT_1);
     int count = 0;
   for(Iterator<Implies> imp = implies.iterator(); imp.hasNext();){
   		count++;
   		Implies impl = imp.next();
   		String bID = impl.bID;
   		String hID = impl.hID;
   		List<Atom> bAtoms = new ArrayList<Atom>();
   		List<Atom> hAtoms = new ArrayList<Atom>();
   		List<Variable> vs = vars;
   		for(Iterator<Body> bds = bodies.iterator(); bds.hasNext();){
   			Body bd = bds.next();
			if(bd.bID.equals(bID)){
				String newAID = bd.aID;
				for(Iterator<Atom> as = atoms.iterator(); as.hasNext();){
					Atom atom = as.next();
					if(newAID.equals(atom.aID)){
						bAtoms.add(atom);
					}
				}
			}		
   		}
   		for(Iterator<Head> hds = heads.iterator(); hds.hasNext();){
   			Head hd = hds.next();
   			if(hd.hID.equals(hID)){
   				String newAID = hd.aID;
   				for(Iterator<Atom> as = atoms.iterator(); as.hasNext();){
   					Atom atom = as.next();
   					if(newAID.equals(atom.aID)){
   						hAtoms.add(atom);
   					}
   				}
   			}
   		}
    stringBuffer.append(TEXT_2);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_3);
    String sName = null;
 		String oName = null;
 		for(int i =0; i < bAtoms.size(); i++){ 		
 		Atom b = bAtoms.get(i);
 		String sID = b.subjID;
 		String oID = b.objID;
 		for(Iterator<Variable> v = vs.iterator(); v.hasNext();){
 			Variable var = v.next();
 			if(sID.equals(var.id))
 				sName = var.name;
 			else if(oID.equals(var.id))
 				oName = var.name;
 		}
    stringBuffer.append(TEXT_4);
    stringBuffer.append(sName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(b.refProperty);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(oName);
    stringBuffer.append(TEXT_7);
    if(i != bAtoms.size() && bAtoms.size() >2)
    stringBuffer.append(TEXT_8);
    else if( i != bAtoms.size() -1)
    stringBuffer.append(TEXT_9);
    else
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    sName = null;
 		oName = null;
 		for(int i=0; i< hAtoms.size(); i++){
 		Atom h = hAtoms.get(i);
 		String sID = h.subjID;
 		String oID = h.objID;
 		for(Iterator<Variable> v = vs.iterator(); v.hasNext();){
 			Variable var = v.next();
 			if(sID.equals(var.id))
 				sName = var.name;
 			else if(oID.equals(var.id))
 				oName = var.name;
 		}
    stringBuffer.append(TEXT_12);
    stringBuffer.append(sName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(h.refProperty);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(oName);
    stringBuffer.append(TEXT_15);
    if(i != hAtoms.size() && hAtoms.size() >2)
    stringBuffer.append(TEXT_16);
    else if( i != hAtoms.size() -1)
    stringBuffer.append(TEXT_17);
    else
    stringBuffer.append(TEXT_18);
    }
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(TEXT_20);
    return stringBuffer.toString();
  }
}
