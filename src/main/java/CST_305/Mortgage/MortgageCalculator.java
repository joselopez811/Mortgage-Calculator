package CST_305.Mortgage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MortgageCalculator {
	
	Locale locale = new Locale("en", "US");      
	NumberFormat format = NumberFormat.getCurrencyInstance(locale);
	
	private double loanAmt;
	private double interest;
	private double months;
	private double balance;
	
	MortgageCalculator(Double loanAmt, double interest, double years) {
		this.loanAmt = loanAmt;
		this.interest = interest/1200;
		this.months = years * 12;
		this.setBalance(0);
	}
	
	public void updateBalance(double payment) {
		this.setBalance(this.getBalance() - payment);
	}
	
	public double calcInterest() {
		return  this.getBalance() * this.getInterest();

	}
	
	public double calcPrincipal() {
		return this.calcPayment() - this.calcInterest();
	}
	
	public double calcPayment() {
		// P = L[c(1 + c)^n]/[(1 + c)^n - 1]
		double L = this.getLoanAmt();
		double c = this.getInterest();
		double n = this.getMonths();
		double payment = (L * (c * Math.pow((1+c), n))/((Math.pow((1+c), n) -1)));
		return payment;
	}
	//B = L[(1 + c)^n - (1 + c)^p]/[(1 + c)^n - 1] 
	//p = months of payments
	
	public void ammortization() throws IOException {
		File file = new File("out.txt"); //created a File object called file
		FileWriter fw = new FileWriter(file, true); // Created a FileWriter object called fw
		PrintWriter pw = new PrintWriter(fw); // created a PrintWriter object called pw
		
		List<String> month = new ArrayList<>();
		month.add("Jan");month.add("Feb");month.add("Mar");month.add("Apr");
		month.add("May");month.add("Jun");month.add("Jul");month.add("Aug");
		month.add("Sep");month.add("Oct");month.add("Nov");month.add("Dec");
		int i = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		double totalInterest = 0;
		double p = 1;
		int x = 1;
		System.out.println("\nYear " + year);
		System.out.format("%14s","Balance");
		System.out.format("%25s", "Payment");
		System.out.format("%17s", "Interest");
		System.out.format("%25s", "Principal");
		System.out.println();
		pw.println("\nYear " + year);
		pw.println("\nBalance\t\t\t\tPayment\t\tInterest\tPrincipal\n");
		
		while(this.getBalance() > 0){
			double balance = this.getBalance();
			pw.print(month.get(i) + " " + x + ".) ");
			pw.println(format.format((balance - this.calcPrincipal())) + "\t|\t" + format.format(this.calcPayment()) + "\t|\t" 
			+ format.format(this.calcInterest()) + " \t|\t" + format.format(this.calcPrincipal()) );
			System.out.print(month.get(i) + " " + x + ".) ");
			System.out.println(format.format((balance - this.calcPrincipal())) + "\t|\t" + format.format(this.calcPayment()) + "\t|\t" 
			+ format.format(this.calcInterest()) + " \t|\t" + format.format(this.calcPrincipal()) );
			
			totalInterest += this.calcInterest();
							
			this.setBalance(p);
			i++;
			if(i == 12) {
				i = 0;
			}
			if(x%12 == 0 && this.getBalance() > 0){
				year++;
				pw.println("\nYear " + year);
				pw.println("\nBalance\t\t\t\tPayment\t\tInterest\tPrincipal\n");
				System.out.println("\nYear " + year);
				System.out.println("\nBalance\t\t\t\tPayment\t\tInterest\tPrincipal\n");
				
		}
			x++;		p++;
		}
		pw.println("\nTotal Interest: " + format.format(totalInterest));
		System.out.println("\nTotal Interest: " + format.format(totalInterest));
	}
	
	public void printAmmortization() throws IOException {
		File file = new File("out.txt2"); //created a File object called file
		FileWriter fw = new FileWriter(file, true); // Created a FileWriter object called fw
		PrintWriter pw = new PrintWriter(fw); // created a PrintWriter object called pw
		
	
		double p = 1;
		int x = 1;
		List<String> month = new ArrayList<>();
		month.add("Jan");month.add("Feb");month.add("Mar");month.add("Apr");
		month.add("May");month.add("Jun");month.add("Jul");month.add("Aug");
		month.add("Sep");month.add("Oct");month.add("Nov");month.add("Dec");		
		int i = 0;
		double totalInterest = 0;
		int year = 2;
		pw.println("\nYear 1");
		pw.println("\nBalance\t\t\tPayment\t\tInterest\tPrincipal\n");
		
		while(this.getBalance() > 0){
			p++;
			this.setBalance(p);

			double balance = this.getBalance();
			
			pw.print(month.get(i) + " " + x + ".)");
			pw.println(format.format(balance) + "\t|\t" + format.format(this.calcPayment()) + "\t|\t" 
			+ format.format(this.calcInterest()) + " \t|\t" + format.format(this.calcPrincipal()) );
			
			totalInterest = totalInterest + this.calcInterest();
			i++;
			if(x%12 == 0 && this.getBalance() > 0){
				pw.println("\nYear " + year);
				pw.println("\nBalance\t\t\tPayment\t\tInterest\tPrincipal\n");
				year++; i = 0;
		}
			x++;		
		}
		pw.println("\nTotal Interest: " + format.format(totalInterest));
		
		
		pw.close();
		
	}
	
	public void ammortizationExtraPayment(double extra) {
		double p = 1;
		int x = 1;
		List<String> month = new ArrayList<>();
		month.add("Jan");month.add("Feb");month.add("Mar");month.add("Apr");
		month.add("May");month.add("Jun");month.add("Jul");month.add("Aug");
		month.add("Sep");month.add("Oct");month.add("Nov");month.add("Dec");
		int i = LocalDate.now().getMonthValue();
		int year = LocalDate.now().getYear();
		double totalInterest = 0;
		
		System.out.println("\nYear " + year);
		System.out.println("\nBalance\t\t\tPayment\t\tInterest\tPrincipal\n");
		
		while(this.getBalance() > 0){
			p++;			
			this.setBalance(p);

			double balance = this.getBalance();

			System.out.print(month.get(i) + " " + x + ".)");
			System.out.println(format.format(balance) + "\t|\t" + format.format(this.calcPayment()) + "\t|\t" 
			+ format.format(this.calcInterest()) + " \t|\t" + format.format(this.calcPrincipal()) );
			
			totalInterest = totalInterest + this.calcInterest();
			i++;
			if(i == 12) {
				i = 0;
			}
			if(x%12 == 0 && this.getBalance() > 0){
				year++;
				System.out.println("\nYear " + year);
				System.out.println("\nBalance\t\t\tPayment\t\tInterest\tPrincipal\n");
				
		}
			x++;		
		}
	
	}
	
	
	
	public void setMonths(double months) {
		this.months = months;
	}

	public double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getMonths() {
		return months;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double p) {
		double L = this.getLoanAmt();
		double c = this.getInterest();
		double n = this.getMonths();
		this.balance = (L * ( ((Math.pow((1+c), n)) - Math.pow((1+c), p))/((Math.pow((1+c), n) - 1))) );
;
	}

	
}
