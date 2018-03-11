package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Archiver 
 * check folder size if exceed 150 mb then archive
 * @author kaustuv
 *
 */
public class Archiver extends TimerTask {

	private final File sourceDirPath, zipFilePath;

	Archiver(String sourceDirPath, String zipFilePath) {
		this.sourceDirPath = new File(sourceDirPath);
		this.zipFilePath = new File(zipFilePath);
	}

	@Override
	public void run() {
		System.out.println(getClass() + " started at " + new Date());
		long getfoldersizeinmb = getfoldersizeinmb(sourceDirPath);
		System.out.println(getfoldersizeinmb);
		if (getfoldersizeinmb > 150) {
			pack();
		}
		System.out.println(getClass() + " ended at " + new Date());
	}

	private long getFolderSize(File folder) {
		long length = 0;
		File[] files = folder.listFiles();

		int count = files.length;

		for (int i = 0; i < count; i++) {
			if (files[i].isFile()) {
				length += files[i].length();
			} else {
				length += getFolderSize(files[i]);
			}
		}
		return length;
	}

	private long getfoldersizeinmb(File folder) {
		return (getFolderSize(folder) / 1024) / 1024;
	}

	private void pack() {
		Path lock = Paths.get(sourceDirPath.getPath() + "/" + "zlock");

		if (!Files.exists(lock)) {
			File zlock = lock.toFile();
			try {

				long currentTimeMillis = System.currentTimeMillis();
				Path p = Files.createFile(Paths.get(zipFilePath.getPath() + "/" + currentTimeMillis + ".zip"));
				try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(p))) {
					Path source = Paths.get(sourceDirPath.getPath());
					Files.walk(source).filter(path -> !Files.isDirectory(path)).forEach(path -> {
						ZipEntry zipEntry = new ZipEntry(source.relativize(path).toString());
						try {
							zipOutputStream.putNextEntry(zipEntry);
							Files.copy(path, zipOutputStream);
							zipOutputStream.closeEntry();
						} catch (IOException e) {
							System.err.println(e);
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// delete folder content
			for (File file : sourceDirPath.listFiles()) {
				if (!file.isDirectory()) {
					System.out.println("Archived " + file.getPath());
					file.delete();
				}
			}
			zlock.delete();
		}
	}
}
