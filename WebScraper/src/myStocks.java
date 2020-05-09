import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class myStocks {
	public static void genTextFile(int num, String company, String link, String[] data) throws FileNotFoundException {
		FileWriter fr, fr2;
		try {
			File dir = new File(company);
			if(!dir.exists()) {
				dir.mkdir();
			}
			fr = new FileWriter(new File(company +"/"+num + ".txt"));
			PrintWriter printer = new PrintWriter(fr);
			fr2 = new FileWriter(new File(company +"/links.txt"),true);
			PrintWriter printer2 = new PrintWriter(fr2);
			printer2.println(link);
			int id =0;
			for(String entry : data) {
				System.out.println("Paragraph " + id +": " + entry);
				id ++;
				printer.println(entry);
			}
			printer.close();
			printer2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main (String [] args) {
		String [] companyList = new String[100];
		String companies = "Wal-Mart Stores\n" + 
			"Exxon Mobil\n" + 
			"Chevron\n" + 
			"Phillips 66\n" + 
			"Berkshire Hathaway\n" + 
			"Apple\n" + 
			"General Motors\n" + 
			"General Electric\n" + 
			"Valero Energy\n" + 
			"Ford Motor\n" + 
			"AT&T\n" + 
			"Fannie Mae\n" + 
			"CVS Caremark\n" + 
			"McKesson\n" + 
			"Hewlett-Packard\n" + 
			"Verizon Communications\n" + 
			"UnitedHealth Group\n" + 
			"J.P. Morgan Chase & Co.\n" + 
			"Cardinal Health\n" + 
			"International Business Machines\n" + 
			"Bank of America Corp.\n" + 
			"Costco Wholesale\n" + 
			"Kroger\n" + 
			"Express Scripts Holding\n" + 
			"Wells Fargo\n" + 
			"Citigroup\n" + 
			"Archer Daniels Midland\n" + 
			"Procter & Gamble\n" + 
			"Prudential Financial\n" + 
			"Boeing\n" + 
			"Freddie Mac\n" + 
			"AmerisourceBergen\n" + 
			"Marathon Petroleum\n" + 
			"Home Depot\n" + 
			"Microsoft\n" + 
			"Target\n" + 
			"Walgreen\n" + 
			"American International Group\n" + 
			"INTL FCStone\n" + 
			"MetLife\n" + 
			"Johnson & Johnson\n" + 
			"Caterpillar\n" + 
			"PepsiCo\n" + 
			"State Farm Insurance Cos.\n" + 
			"ConocoPhillips\n" + 
			"Comcast\n" + 
			"WellPoint\n" + 
			"Pfizer\n" + 
			"Amazon.com\n" + 
			"United Technologies\n" + 
			"Dell\n" + 
			"Dow Chemical\n" + 
			"United Parcel Service\n" + 
			"Intel\n" + 
			"Google\n" + 
			"Lowe's\n" + 
			"Coca-Cola\n" + 
			"Merck\n" + 
			"Lockheed Martin\n" + 
			"Cisco Systems\n" + 
			"Best Buy\n" + 
			"Safeway\n" + 
			"FedEx\n" + 
			"Enterprise Products Partners\n" + 
			"Sysco\n" + 
			"Walt Disney\n" + 
			"Johnson Controls\n" + 
			"Goldman Sachs Group\n" + 
			"CHS\n" + 
			"Abbott Laboratories\n" + 
			"Sears Holdings\n" + 
			"DuPont\n" + 
			"Humana\n" + 
			"World Fuel Services\n" + 
			"Hess\n" + 
			"Ingram Micro\n" + 
			"Plains All American Pipeline\n" + 
			"Honeywell International\n" + 
			"United Continental Holdings\n" + 
			"Oracle\n" + 
			"Liberty Mutual Insurance Group\n" + 
			"HCA Holdings\n" + 
			"Delta Air Lines\n" + 
			"Aetna\n" + 
			"Deere\n" + 
			"Supervalu\n" + 
			"Sprint Nextel\n" + 
			"Mondelēz International\n" + 
			"New York Life Insurance\n" + 
			"American Express\n" + 
			"News Corp.\n" + 
			"Allstate\n" + 
			"Tyson Foods\n" + 
			"Massachusetts Mutual Life Insurance\n" + 
			"Tesoro\n" + 
			"Morgan Stanley\n" + 
			"TIAA-CREF\n" + 
			"General Dynamics\n" + 
			"Philip Morris International\n" + 
			"Nationwide";

		Scanner scan = new Scanner(companies);
		for(int i =0; i < 100; i ++) {
			companyList[i] = scan.nextLine();
		}
                
		for(int i =0; i < 100; i ++) {
			NewsParser newsParser = new NewsParser(companyList[i]);
			String [] links = newsParser.retrieveLinksNYT();
			int num =0;
			for(String link : links) {
				ArticleParser articleParser = new ArticleParser(link);
				try {
					genTextFile(num,newsParser.company,link,articleParser.retrieveParagraphs());
					num++;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
