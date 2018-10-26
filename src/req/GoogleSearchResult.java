import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class GoogleSearchResult {
	public static String request;
	private final static String urlRequest = "http://www.google.com/search?q=";
	private final static String userAgent = "\"User-Agent\", \"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2\"";

	static String setRequest() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Введите данные для поиска");
		request = urlRequest+sc.nextLine();
		sc.close();
		return request;
	}
	
	static void sendRequest(String request){
		try {
		URL googleSearchURL=new URL(request);
		HttpURLConnection connection=(HttpURLConnection) googleSearchURL.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", userAgent);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
		FileWriter nFile = new FileWriter("searchResult.html");
		BufferedWriter bw = new BufferedWriter(nFile);
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			bw.write(inputLine);}
		in.close();
		try {
			bw.close();
		}catch(IOException e) {
			System.out.println("Ошибка при закрытии файла");
		}
		}catch(MalformedURLException e) {
			System.out.println("Ошибка отправки запроса");
		}catch(IOException e) {
			System.out.println("Ошибка при записи в файл");
		}
	}
	
	
	
	public static void main(String[] args){
		String request=GoogleSearchResult.setRequest();
		GoogleSearchResult.sendRequest(request);
	}

}
