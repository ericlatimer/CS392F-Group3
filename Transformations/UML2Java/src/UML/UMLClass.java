package UML;

public class UMLClass {
	
	public int id;
	public String name;
	public String visibility;
	public int pid;
	public boolean isAbstract;
	
	public UMLClass(int id, String name, String visibility, int pid, boolean abs)
	{
		this.id = id;
		this.name = name;
		this.visibility = visibility;
		this.pid = pid;
		this.isAbstract = abs;
	}
}
