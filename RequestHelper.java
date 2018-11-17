import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestHelper {

	private final static String USER_AGENT = "\"User-Agent\", \"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2\"";
	private final static String REQUEST_METHOD = "GET";
	private final static String REQUEST_PROPERTY = "User-Agent";
	private final static int HTTP_STATUS_OK = 200;

	public static String sendRequest(String urlString) {
		StringBuilder builder = new StringBuilder(5000);
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(REQUEST_METHOD);
			connection.setRequestProperty(REQUEST_PROPERTY, USER_AGENT);
			if (connection.getResponseCode() == HTTP_STATUS_OK) {
				inputStream = connection.getInputStream();
			} else {
				inputStream = connection.getErrorStream();
			}
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}
		} catch (MalformedURLException e) {
			Logger.printLog(e);
		} catch (IOException e) {
			Logger.printLog(e);
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				Logger.printLog(e);
			}
		}
		return builder.toString();
	}

	public static void writeToFile(String fileName, String content) {
		try (FileWriter htmlWriter = new FileWriter("SearchPages/" + fileName + ".html")) {
			htmlWriter.write(content);
		} catch (IOException e) {
			Logger.printLog(e);
		}
	}
}
