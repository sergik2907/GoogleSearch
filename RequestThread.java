public class RequestThread implements Runnable {

	String request;
	String fileName;

	public RequestThread(String request, String fileName) {
		this.request = request;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		String content = RequestHelper.sendRequest(request);
		RequestHelper.writeToFile(fileName, content);
	}
}
