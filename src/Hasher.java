import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

public class Hasher {
	private final static Logger LOGGER = Logger.getLogger(Hasher.class);

	public static String hashRequest(String request) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(request.getBytes(StandardCharsets.UTF_8));
			byte[] digest = messageDigest.digest();
			String heshedRequest = String.format("%064x", new BigInteger(1, digest));
			return heshedRequest;
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e);
		}
		return null;
	}
}
