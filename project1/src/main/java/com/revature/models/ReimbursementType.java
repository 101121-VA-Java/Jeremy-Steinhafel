package com.revature.models;

public class ReimbursementType {
	public static int LODGING = 1;
	public static int TRAVEL = 2;
	public static int FOOD = 3;
	public static int OTHER = 4;

	private int typeID;
	private String type;
	
	public int getTypeID() {
		return typeID;
	}
	
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public ReimbursementType(int typeID, String type) {
		super();
		this.typeID = typeID;
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + typeID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementType other = (ReimbursementType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typeID != other.typeID)
			return false;
		return true;
	}
	
}
