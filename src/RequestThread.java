import java.io.BufferedReader;
import java.util.Map;

public class RequestThread implements Runnable{
	Thread thrd;
	String request;
	
public RequestThread(String request){
	thrd=new Thread(this, request);
	thrd.start();
}
	@Override
	public void run() {
		GetRequests getRequests = new GetRequests();
		Map<String, String> requestMap=getRequests.getRequestHashMap();
		for(Map.Entry<String, String> entry:requestMap.entrySet()) {
			if(thrd.getName().equals((String)entry.getValue())) {
				SendRequestAndWriteHtml sendRequestAndWriteHtml = new SendRequestAndWriteHtml();
				BufferedReader bufferedReader= sendRequestAndWriteHtml.sendRequest((String) entry.getValue());
				sendRequestAndWriteHtml.WriteHtml(bufferedReader, (String) entry.getKey());
		}
	}
}}
