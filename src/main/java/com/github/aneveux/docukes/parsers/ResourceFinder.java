package com.github.aneveux.docukes.parsers;

import java.io.File;
import java.util.List;

/**
 * Simple ResourceFinder allowing to search for particular files
 *
 * @author aneveux
 * @version 1.0
 *
 */
public class ResourceFinder {

	/**
	 * Recursively explores a directory, searching for files with a particular
	 * extension
	 *
	 * @param dir
	 *            the directory in which we need to search for files
	 * @param files
	 *            a list containing all the files found in the directory
	 * @param extensionFilter
	 *            the extension filter to be used while searching for files
	 * @return a list containing all the files matching with the extension
	 */
	public static List<File> explore(File dir, List<File> files,
			String extensionFilter) {
		if (!dir.isDirectory()) {
			if (dir.isFile() && dir.getName().endsWith(extensionFilter))
				files.add(dir);
			return files;
		} else {
			for (final File children : dir.listFiles())
				explore(children, files, extensionFilter);
			return files;
		}
	}

}
