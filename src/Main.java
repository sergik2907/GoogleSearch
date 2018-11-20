import java.util.Map;

public class Main {

	public static void main(String[] args) {
		ThreadPool threadPool = new ThreadPool(5);
		Map<String, String> requestMap = Config.getRequestMap();
		for (String key : requestMap.keySet()) {
			String value = requestMap.get(key);
			threadPool.execute(new RequestThread(value, key));
		}
		threadPool.start();
	}
}
