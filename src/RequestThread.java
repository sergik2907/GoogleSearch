import org.apache.log4j.Logger;

public class RequestThread implements Runnable {

	String request;
	String fileName;
	final static Logger LOGGER = Logger.getLogger(RequestThread.class);

	public RequestThread(String request, String fileName) {
		this.request = request;
		this.fileName = fileName;
	}
	
	public String getRequest() {
		return this.request;
	}

	@Override
	public void run() {
		String content = RequestHelper.sendRequest(request);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
		RequestHelper.writeToFile(fileName, content);
		LOGGER.info("done " + request);
	}
}
