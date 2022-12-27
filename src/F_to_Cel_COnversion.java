import java.util.Scanner;
import java.util.Vector;

public class F_to_Cel_COnversion {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		Vector<Double> Fahrenheit = new Vector<Double>();
		Vector<Double> Celsius = new Vector<Double>();

		boolean choice = true;

	    while(choice == true){
	    	System.out.println("Enter the temp");
	    	Fahrenheit.add(sc.nextDouble());
	        System.out.println("Enter true for Yes & Enter false for no");
	        choice=sc.nextBoolean();
	    }
	    for (int i = 0; i < Fahrenheit.size(); i++) {
	    	double a=(Fahrenheit.get(i)-32);
	    	double c=(a*5)/9;
	    	Celsius.add(c);
        }
	    for (int i = 0; i < Fahrenheit.size(); i++) {
            System.out.println("Fahrenheit temp = "+Fahrenheit.get(i) + " ");
        }
	    for (int i = 0; i < Celsius.size(); i++) {
            System.out.println("Celsius temp = "+Celsius.get(i) + " ");
        }
	    
	}
}
