
public class TestMain {

	public static void main(String[] args) {
		String name = "Sergey";
		String hashedRequest = Hasher.hashRequest(name);
		System.out.println(hashedRequest);
	}
}