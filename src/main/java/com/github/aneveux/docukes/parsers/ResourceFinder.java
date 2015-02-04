/*
 * Docukes is a simple framework allowing to generate documentation out of cucumber java classes.
 * Copyright (C) 2015 Antoine Neveux or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
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
