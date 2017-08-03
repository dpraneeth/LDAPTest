package Strings;

public class ReadingString {

	String a1 = "Srikanth";
	String a2 = "Swathi";
	String a3;

	public static void main(String[] args) {
		ReadingString s1 = new ReadingString();
		s1.concat();
		}
      
      public void concat(){
    		String a3 = a1.concat(a2);
    		System.out.println("couple name is "+a3);
      }
} 
 
