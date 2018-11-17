import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<String, String> requestMap = Config.getRequestMap();
		for (String key : requestMap.keySet()) {
			String value = requestMap.get(key);
			Runnable runnable = new RequestThread(value, key);
			Thread thread = new Thread(runnable);
			thread.start();
		}
	}
}