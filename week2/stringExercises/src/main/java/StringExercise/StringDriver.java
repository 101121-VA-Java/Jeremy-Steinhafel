package StringExercise;

public class StringDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StringDriver sd = new StringDriver();
		
		System.out.println(sd.removeEveryOtherLetter("Hello World!"));

	}
	
	/*
	 * Using StringBuilder and its associated methods (if needed), 
	 * create a method that removes every other letter of a String passed in 
	 * and return that newly created string.
	 */
	public String removeEveryOtherLetter(String s) {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < s.length(); i+=2) {
				str.append(s.charAt(i));
		} 
		return str.toString();
	}

	/*
	 * Given the string of a url of the format localhost:3500/[controller]/[resource]
	 * where "localhost:3500" is a constant
	 * controller and resource can be any 1 word
	 * return the value of controller as a string
	 */
	public String getControllerValue(String url) {
		return null;
	}

}
