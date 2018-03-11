package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.TimerTask;

/**
 * temp -> secure
 * @author kaustuv
 *
 */
public final class FileProcessor extends TimerTask {

	private final File sourceLocation;
	private final File targetLocation;

	FileProcessor(String source, String target) {
		this.sourceLocation = new File(source);
		this.targetLocation = new File(target);
	}

	public void copy(File sourceLocation, File targetLocation) throws IOException {
		if (sourceLocation.isDirectory()) {
			copyDirectory(sourceLocation, targetLocation);
		} else {
			copyFile(sourceLocation, targetLocation);
		}
	}

	private void copyDirectory(File source, File target) throws IOException {
		if (!target.exists()) {
			target.mkdir();
		}

		for (String f : source.list()) {
			if(!f.contains(".sh") || !f.contains(".bat"))
			copy(new File(source, f), new File(target, f));
		}
	}

	private void copyFile(File source, File target) throws IOException {
		if (!target.exists() && source.getTotalSpace() != target.getTotalSpace()) {
			try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(target)) {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			}
		}
	}

	public void execute() {
		try {
			copy(this.sourceLocation, this.targetLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println(getClass() + " started at " + new Date());
		Path lock = Paths.get(targetLocation.getPath() + "/" + "zlock");
		if (!Files.exists(lock)) {
			execute();
		}
		System.out.println(getClass() + " ended at " + new Date());
	}

}
