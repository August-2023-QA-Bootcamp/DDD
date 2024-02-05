package constants;

public enum Attribute {

	// MAX_LENGTH is a Constant, Inside the parentheses, it represents the actual attribute
	MAX_LENGTH("maxlength"),
	INNER_TEXT("innerHTML");
	
	String attribute;
	
	// parameterized Constructor
	// Constructor inside Enum are always private type
	private Attribute(String attribute) {
		this.attribute = attribute;
	}
	
	// getter method
	// why getter method here?
	// When Enum call the Constant, actually it call the getter method
	// https://issac88.medium.com/java-enum-fields-methods-constructors-3a19256f58b
	public String getAttribute() {
		return attribute;
	}
	
	@Override
	public String toString() {
		return attribute;
	}
}
