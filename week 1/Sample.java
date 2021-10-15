public class Sample {
	public static void main (String[] args) {
		int i = 10;
		do {
			if(i==10){
				System.out.println("Count Down starts now!");
			}
			System.out.println(i);
			i--;
			if(i==0){
				System.out.println("Go!");
			}
		} while(i>0);
	}
}
