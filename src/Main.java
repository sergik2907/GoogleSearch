
public class Main {

	public static void main(String[] args) {
		GetRequests getRequests = new GetRequests();
		String[] requestArray=getRequests.getRequestArray();
		RequestThread[] thread = new RequestThread[requestArray.length];
		for(int i=0;i<requestArray.length; i++) {
			thread[i]=new RequestThread(requestArray[i]);
		}
	}
	}