import java.io.*;
import java.net.*;
import java.util.List;

class NewsParser {
	public String company;
	private String urlBaseNYT = "https://www.nytimes.com/search?query=";
	private Scraper scraper;
	public NewsParser(String company) {
		this.company = company;
		this.scraper = new Scraper(urlBaseNYT+company.replaceAll(" ","-"));
	}
	public String[] retrieveLinksNYT() {
		List<String> links = null;
		String[] linksFinal;
		try {
			links = scraper.retrieveLinks();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i =0;
		for (String link : links) {
			if(link.contains("searchResultPosition=")) {
				i ++;
			}
		}
		linksFinal = new String[i];
		int k =0;
		for (String link : links) {
			if(link.contains("searchResultPosition=")) {
				linksFinal[k] = link;
				k++;
			}
		}
		return linksFinal;
	}
	public static void main (String args[]) {
		NewsParser parser = new NewsParser("Google");
		String[] links = parser.retrieveLinksNYT();
		for(String link : links) {
			System.out.println(link);
		}
	}
}
