import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.log4j.Logger;

public class ThreadPool {

	final static Logger LOGGER = Logger.getLogger(ThreadPool.class);
	private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
	private volatile boolean isRunning = true;
	private final int nThreads;

	public ThreadPool(int nThreads) {
		this.nThreads = nThreads;
	}

	public void start() {
		for (int i = 0; i < nThreads; i++) {
			new Thread(new TaskWorker(i + 1)).start();
		}
	}

	public void execute(Runnable command) {
		workQueue.offer(command);
	}

	public void shutdown() {
		isRunning = false;
	}

	private final class TaskWorker implements Runnable {

		int id = 1;

		public TaskWorker(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			while (isRunning && !workQueue.isEmpty()) {
				RequestThread nextTask = (RequestThread) workQueue.poll();
				if (nextTask != null) {
					Thread t = new Thread(nextTask);
					LOGGER.info(id + " Thread " + nextTask.getRequest() + " begin");
					t.start();
					try {
						t.join();
					} catch (InterruptedException e) {
						LOGGER.error(e);
					}
					//LOGGER.info(id + " Thread " + nextTask.getRequest() + " done");

				}
			}
		}
	}
}
