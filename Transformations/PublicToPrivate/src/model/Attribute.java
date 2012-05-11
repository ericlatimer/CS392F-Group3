package model;

public class Attribute {

	public String attID;
	public String classID;
	public String attName;
	public String visibility;
	public boolean isSpecification;
	public String ownerScope;
	public String changeability;
	public String dataTypeID;

	public Attribute(String attID, String classID, String attName,
			String visibility, boolean isSpecification, String ownerScope,
			String changeability, String dataTypeID) {
		this.attID = attID;
		this.classID = classID;
		this.attName = attName;
		this.visibility = visibility;
		this.isSpecification = isSpecification;
		this.ownerScope = ownerScope;
		this.changeability = changeability;
		this.dataTypeID = dataTypeID;
	}
}
