package UML;

public class Attribute {
	int id;
	public String name;
	public String type;
	int pid;
	public String ownerscope;
	public String visibility;
	boolean changeability;
	public int ownerid;
	
	public Attribute(int id, String name, String type, int pid, String os, String visibility, boolean change, int oid)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.pid = pid;
		this.ownerscope = os;
		this.visibility = visibility;
		this.changeability = change;
		this.ownerid = oid;
	}
	
	
	
}
