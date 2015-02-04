package com.github.aneveux.docukes.parsers;

import static com.github.aneveux.docukes.Constants.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.Import;
import org.jboss.forge.roaster.model.source.JavaClassSource;

/**
 * Simple helper allowing to detect if a Java class actually contains something
 * related to Cukes.
 *
 * It scans the imports for finding out if a class is a cuke or not. It maybe is
 * not the best solution ever, but at least it'll do the trick for now...
 *
 * @author aneveux
 * @version 1.0
 *
 */
public class CukesDetector {

	/**
	 * Simply detects if a Java class contains some cukes related stuff
	 *
	 * @param javaClazz
	 *            class that needs to be scanned
	 * @return true if it's a cukes
	 */
	public static boolean isCukes(File javaClazz) {
		JavaClassSource clazz;
		try {
			clazz = Roaster.parse(JavaClassSource.class, javaClazz);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		for (final Import importz : clazz.getImports())
			if (importz.getPackage().startsWith(CUKES_IDENTIFIER))
				return true;
		return false;
	}
}
