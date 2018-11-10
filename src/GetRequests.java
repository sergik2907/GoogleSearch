import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetRequests {
	private static String urlRequest = "http://www.google.com/search?q=";

	public HashMap<String, String> getRequestHashMap() {

		Map<String, String> requestMap = new HashMap<String, String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("searchRequest.txt"));
			String line;
			String hashline;
			while ((line = reader.readLine()) != null) {
				hashline = Hasher.hashRequest(line);
				line = urlRequest.concat(line);
				requestMap.put(hashline, line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			Logger.printLog(e);
		} catch (IOException e) {
			Logger.printLog(e);
		}

		return (HashMap<String, String>) requestMap;

	}
	
	public String[] getRequestArray() {
		GetRequests getRequests = new GetRequests();
		Map<String, String> requestMap=getRequests.getRequestHashMap();
		String[] requestArray= new String[requestMap.size()];
		int i=0;
		for(Map.Entry<String, String> entry:requestMap.entrySet()) {
			requestArray[i]=entry.getValue();
			i++;
		}
		return requestArray;
}
}