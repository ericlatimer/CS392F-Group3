package model;

public class Class {

	public String classID;
	public String className;
	public String visibility;
	public boolean isSpecification;
	public boolean isRoot;
	public boolean isLeaf;
	public boolean isAbstract;
	public boolean isActive;

	public Class(String classID, String className, String visibility,
			boolean isSpecification, boolean isRoot, boolean isLeaf,
			boolean isAbstract, boolean isActive) {
		this.classID = classID;
		this.className = className;
		this.visibility = visibility;
		this.isSpecification = isSpecification;
		this.isRoot = isRoot;
		this.isLeaf = isLeaf;
		this.isAbstract = isAbstract;
		this.isActive = isActive;
	}
}
