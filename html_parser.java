package reference;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//ref => https://jsoup.org/
public class html_parser {
	public static void main(String[] args) throws Exception {
		Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
		System.out.println(doc.title());
		Elements newsHeadlines = doc.select("#mp-itn b a");
		for (Element headline : newsHeadlines) {
			System.out.printf("%s\n\t%s",headline.attr("title"), headline.absUrl("href"));
		}
	}
}
