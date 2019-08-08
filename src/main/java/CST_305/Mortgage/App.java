package CST_305.Mortgage;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
		MortgageCalculator one = new MortgageCalculator((double) 137000, 3.375, 15);
		one.ammortization();
//		one.printAmmortization();
    }
//    Month	Balance (Start)	Payment		Principal		Interest	Balance (End)
//    1	$ 20,000.00			$ 377.42	$ 294.09		$ 83.33		$ 19,705.91
//    2	$ 19,705.91			$ 377.42	$ 295.32		$ 82.11		$ 19,410.59
//    3	$ 19,410.59			$ 377.42	$ 296.55		$ 80.88		$ 19,114.04
//    4	$ 19,114.04			$ 377.42	$ 297.78		$ 79.64		$ 18,816.26
}
