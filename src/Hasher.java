import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class Hasher {
	private final static Logger LOGGER = LoggerConfig.fileLogger();
	
	public static String hashRequest(String request) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(request.getBytes(StandardCharsets.UTF_8));
			byte[] digest = messageDigest.digest();
			String heshedRequest = String.format("%064x", new BigInteger(1, digest));
			return heshedRequest;
		} catch (NoSuchAlgorithmException e) {
			LOGGER.info(e.getMessage());
			}
		return null;
	}
}
