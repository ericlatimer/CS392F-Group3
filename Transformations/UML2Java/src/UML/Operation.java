package UML;

public class Operation {
	public int id;
	public String name;
	public String type;
	int pid;
	public String ownerscope;
	public String visibility;
	public int ownerid;
	
	public Operation(int id, String name, String type, int pid, String os, String visibility, int oid)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.pid = pid;
		this.ownerscope = os;
		this.visibility = visibility;
		this.ownerid = oid;
	}
	
}
