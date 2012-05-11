package model;

public class DataType {

	public String dataTypeID;
	public String name;
	public boolean isSpec;
	public boolean isRoot;
	public boolean isLeaf;
	public boolean isAbstract;

	public DataType(String dataTypeID, String name, boolean isSpec,
			boolean isRoot, boolean isLeaf, boolean isAbstract) {
		this.dataTypeID = dataTypeID;
		this.name = name;
		this.isSpec = isSpec;
		this.isRoot = isRoot;
		this.isLeaf = isLeaf;
		this.isAbstract = isAbstract;
	}
}
