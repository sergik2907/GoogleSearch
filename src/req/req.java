package req;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class req {

	private static void requestGoogle(String request) throws UnsupportedEncodingException, IOException {
		String google = "http://www.google.com/search?q=";
		String charset = "UTF-8";
		String userAgent = "\"User-Agent\", \"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2\""; // Change this to your company name and bot homepage!

		Elements links = Jsoup.connect(google + URLEncoder.encode(request, charset)).userAgent(userAgent).get().select(".g>.r>a");
		FileWriter writer = new FileWriter("request.txt", false);
		for (Element link : links) {
		    String title = link.text();
		    String url = link.absUrl("href");
		    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

		    if (!url.startsWith("http")) {
		        continue;
		    }
		    
		    writer.write("\n\ntitle:   "+title+" ");
		    writer.write("\n\nURL:   "+url+" ");
		    writer.write("\n____________________________");
		}
		writer.close();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("¬ведите ваш запрос");
		String request = scanner.nextLine();
		requestGoogle(request);
		scanner.close();
		}

}
