import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendRequestAndWriteHtml {
	private final static String userAgent = "\"User-Agent\", \"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2\"";
	private final static String requestMethod="GET";
	private final static String requestProperty="User-Agent";
	
	public BufferedReader sendRequest(String request){
		try {
			URL googleSearchUrl=new URL(request);
			HttpURLConnection connection=(HttpURLConnection) googleSearchUrl.openConnection();
			connection.setRequestMethod(requestMethod);
			connection.setRequestProperty(requestProperty, userAgent);
			BufferedReader bufferedReader = new BufferedReader(
			        new InputStreamReader(connection.getInputStream()));
			return bufferedReader;
		} catch (MalformedURLException e) {
			Logger.printLog(e);
		} catch (IOException e) {
			Logger.printLog(e);
		}
		return null;
	}
	
	public void WriteHtml(BufferedReader bufferedReader, String fileName) {
		try {
			FileWriter HtmlFile = new FileWriter("SearchPages/"+fileName+".html");
			BufferedWriter bufferedWriter = new BufferedWriter(HtmlFile);
			String inputLine;
			while ((inputLine = bufferedReader.readLine()) != null) {
				bufferedWriter.write(inputLine);}
			bufferedReader.close();
			try {
				bufferedWriter.close();
			}catch(IOException e) {
				Logger.printLog(e);
			}
		} catch (IOException e) {
			Logger.printLog(e);
		}
	}
	
}
