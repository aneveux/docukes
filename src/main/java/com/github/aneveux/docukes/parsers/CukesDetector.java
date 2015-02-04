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

import static com.github.aneveux.docukes.Constants.CUKES_IDENTIFIER;

import java.io.File;
import java.io.FileNotFoundException;

import org.jboss.forge.roaster.ParserException;
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
		} catch (final ParserException pe) {
			System.out.println(javaClazz.getName() + " isn't a java class!");
			return false;
		}
		for (final Import importz : clazz.getImports())
			if (importz.getPackage().startsWith(CUKES_IDENTIFIER))
				return true;
		return false;
	}
}
