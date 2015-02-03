package com.github.aneveux.docukes.parsers;

import java.io.File;
import java.util.List;

public class ClazzLocator {

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
