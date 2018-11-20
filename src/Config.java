import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class Config {

	final static Logger LOGGER = Logger.getLogger(Config.class);
	private static String URL_REQUEST = "http://www.google.com/search?q=";

	public static Map<String, String> getRequestMap() {
		Map<String, String> requestMap = new HashMap<String, String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("searchRequest1.txt"));
			String searchWord;
			String searchUrl;
			String hash;
			while ((searchWord = reader.readLine()) != null) {
				hash = Hasher.hashRequest(searchWord);
				searchUrl = URL_REQUEST.concat(searchWord);
				requestMap.put(hash, searchUrl);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}
		return requestMap;
	}
}
