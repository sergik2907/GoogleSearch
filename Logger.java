import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

	public static void printLog(Exception e) {
		FileWriter nFile;
		try {
			nFile = new FileWriter("log.txt", true);
			BufferedWriter bw = new BufferedWriter(nFile);
			Date date = new Date();
			String logMessage = date.toString() + ": " + e.toString() + ";\n";
			bw.write(logMessage);
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
