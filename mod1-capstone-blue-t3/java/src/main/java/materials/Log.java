package materials;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Log {

	public Log(String logFile) {

	}

	public String writeToLog() {

		LocalDateTime theDateAndTime = LocalDateTime.now();
		String logText = "";
		File newFile = new File("Log.txt");
		try (PrintWriter logOut = new PrintWriter(new FileOutputStream(newFile.getAbsoluteFile(), true))) {
			
			logText = logText + theDateAndTime;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return logText;

		// need to run function, like writeToLog or something
		// so we can write something like, log.writeToLog, in each purchase/add
		// money/get change action
		// the getDate function will be a part of the writeToLog function, as such will
		// be in the Log class
		// log.txt, needs to be appended to, and never overwritten.
		//
	}
}
