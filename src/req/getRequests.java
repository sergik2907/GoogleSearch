import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class getRequests {
	private static String urlRequest="http://www.google.com/search?q=";
		public static HashMap<String, String> getRequest(){ 
			
			Map<String, String> requestMap = new HashMap<String, String>();
			try {
				BufferedReader reader = new BufferedReader(new FileReader("searchRequet.txt"));
				String line;
				String hashline;
				while((line=reader.readLine())!=null) {
					hashline=String.valueOf(line.hashCode());
					line=urlRequest.concat(line);
					requestMap.put(hashline, line);
					
				}
				for(Map.Entry entry:requestMap.entrySet()) {
					System.out.println("key: "+entry.getKey()+" Value: "+entry.getValue());
				}
			} catch (FileNotFoundException e) {
				Logger.printLog(e);
			} catch (IOException e) {
				Logger.printLog(e);
			}
			
			return (HashMap<String, String>) requestMap;
		
	}
		
		public static void main(String[] args) {
			getRequest();
		}
}
