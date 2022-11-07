package roulette.model;

public class Prize {

	public enum ProductType {
		ELECTRONICS, CHILDREN, OCIO
	}

	private String code;
	private ProductType type;
	private String name;
	private int points;

	public Prize(String code, String type, String name, int points) {
		this.code = code;
		this.type = parseProductType(type);
		this.name = name;
		this.points = points;
	}

	private ProductType parseProductType(String str) {
		switch (str.toLowerCase()) {
		case "electronics":
			return ProductType.ELECTRONICS;
		case "children":
			return ProductType.CHILDREN;
		case "ocio":
			return ProductType.OCIO;
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public ProductType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.code);
		buffer.append(" - ");
		buffer.append(String.valueOf(this.type));
		buffer.append(" - ");
		buffer.append(this.name);
		buffer.append(" - ");
		buffer.append(String.valueOf(this.points));
		buffer.append(" points");
		return buffer.toString();
	}
	
	public boolean equals(Prize other) {
		return this.getCode().equals(other.getCode());
	}

}
