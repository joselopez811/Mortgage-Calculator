package CST_305.Mortgage;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class CalculatorMenu {

	static Locale locale = new Locale("en", "US");      
	static NumberFormat format = NumberFormat.getCurrencyInstance(locale);
	
	public static void displayCalc() {
		Scanner scan = new Scanner(System.in);
		System.out.println("                    Mortgage Calculator");
		System.out.print("Loan amount: $"); double loan = scan.nextDouble();
		System.out.print("Interest: (_.__%) :"); double interest = scan.nextDouble();
		System.out.print("Years: "); double years = scan.nextDouble();
		MortgageCalculator one = new MortgageCalculator(loan, interest, years);
		showSchedule(one);
	}
	public static void displayCalculations(MortgageCalculator one){
		System.out.println(format.format(one.getBalance()) + "\t" + format.format(one.calcPayment()) + "\t\t" 
				+ format.format(one.calcInterest()) + "\t\t" + format.format((one.calcPayment()-one.calcInterest())));
		
	}
	public static void showSchedule(MortgageCalculator one){

		System.out.println("\nBalance\t\tPayment\t\tInterest\tPrincipal");
		int x = 1;
		while(one.getBalance() > 0) {
			System.out.print(x + ".) ");
			x++;
			displayCalculations(one);
			one.updateBalance(one.calcPayment());
		}
		
		
	}
	
}
