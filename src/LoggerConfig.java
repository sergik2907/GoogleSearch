import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {
	private final static String LOGGER_NAME = "MyLog";
	private final static String LOGGER_FILE = "log/MyLogFile.log";
	private final static Logger LOGGER = Logger.getLogger(LOGGER_NAME);

	public static Logger fileLogger() {
		try {
			FileHandler fileHandler = new FileHandler(LOGGER_FILE);
			LOGGER.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
		} catch (SecurityException e) {
			LOGGER.info(e.getMessage());
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		}
		return LOGGER;
	}
}