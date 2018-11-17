import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Config {

	private static String URL_REQUEST = "http://www.google.com/search?q=";
	private static final Logger LOGGER = LoggerConfig.fileLogger();

	public static Map<String, String> getRequestMap() {
		Map<String, String> requestMap = new HashMap<String, String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("searchRequest.txt"));
			String searchWord;
			String searchUrl;
			String hash;
			while ((searchWord = reader.readLine()) != null) {
				hash = Hasher.hashRequest(searchWord);
				searchUrl = URL_REQUEST.concat(searchWord);
				requestMap.put(hash, searchUrl);
			}
		} catch (FileNotFoundException e) {
			LOGGER.info(e.getMessage());
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				LOGGER.info(e.getMessage());
			}
		}
		return requestMap;
	}
}