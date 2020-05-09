import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ArticleParser {
	private String url;
	public ArticleParser(String url) {
		this.url = url;
	}
	public String[] retrieveParagraphs() throws IOException {
		String [] paragraphArr;
		Document doc = Jsoup.connect(url).get();
		Elements paragraphs = doc.select("p");
		
		int count = 0;
		for(Element p : paragraphs) {
			if(p.text().length() <= 5120 && p.text().length() >= 200) {
				count ++;
			}
		}
		
		paragraphArr = new String[count];
		count =0;
		for (int i =0; i < paragraphs.size(); i ++) {
			if(paragraphs.get(i).text().length() <= 5120 && paragraphs.get(i).text().length() >= 200) {
				paragraphArr[count] =paragraphs.get(i).text();
				count ++;
			}
		}
		return paragraphArr;
	}
	public static void main(String [] args) {
		ArticleParser parser = new ArticleParser("https://www.nytimes.com/2019/10/23/technology/quantum-computing-google.html?searchResultPosition=1");
		try {
			String paragraphs [] = parser.retrieveParagraphs();
			for(String link : paragraphs) {
				System.out.println(link);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
