package fruit;

public class Fruit {

	String id;
	String name;
	
	public Fruit(String id, String name) {
		this.id = id;
		this.name = name;
	}	
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
}