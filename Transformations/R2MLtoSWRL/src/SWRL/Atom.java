package SWRL;

public class Atom {
	public String aID;
	public String refProperty;
	public String subjID;
	public String objID;
	
	
	public Atom()
	{
		aID = null;
		refProperty = null;
		subjID = null;
		objID = null;
	}
	public Atom(String a, String rP, String s, String o){
		aID = a;
		refProperty = rP;
		subjID = s;
		objID = o;
	}

}
