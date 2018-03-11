package app;

import java.io.File;
import java.util.Timer;


/**
 * Main App Monitor 
 * @author kaustuv
 *
 */
public class MainApp {

	private static void folderCreate(File theDir) {
		if (!theDir.exists()) {
			System.out.println("creating directory: " + theDir.getName());
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println(theDir.getPath() + " created");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		File securedFile = new File("c://secured/");
		// if the directory does not exist, create it
		folderCreate(securedFile);
		File archeive = new File("c://archive/");
		FileProcessor fp = new FileProcessor("c://tmp/", securedFile.getPath());
		Archiver archiever = new Archiver(securedFile.getPath(), archeive.getPath());
        //running timer task as daemon thread
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(fp, 0, 120000);
        timer.scheduleAtFixedRate(archiever, 0, 180000);
        System.out.println("TimerTask started");

	}
}
