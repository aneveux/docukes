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

import static com.github.aneveux.docukes.Constants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

/**
 * Simple parser using Roaster in order to get methods actually annotated with
 * some cucumber stuff
 *
 * @author aneveux
 * @version 1.0
 *
 */
public class CukesParser {

	/**
	 * The Java class that we need to parse
	 */
	protected JavaClassSource clazz;

	/**
	 * Instanciates a parser for a particular Java class
	 *
	 * @param javaClazz
	 *            the java class that needs to be parsed
	 */
	public CukesParser(File javaClazz) {
		try {
			clazz = Roaster.parse(JavaClassSource.class, javaClazz);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the Roaster mapping of a Java class
	 *
	 * @return object representation of the Java class provided as a File
	 */
	public JavaClassSource getClazz() {
		return clazz;
	}

	/**
	 * Scans the java class and returns all the methods annotated with some
	 * cucumber stuff
	 * 
	 * @return a list of methods containing cukes annotations
	 */
	public List<MethodSource<JavaClassSource>> getCukesMethods() {
		final List<MethodSource<JavaClassSource>> methods = new ArrayList<MethodSource<JavaClassSource>>();
		// Solution using loops and not direct types of annotations because of
		// i18n in cukes annotations... Would lead to too many choices ;)
		for (final MethodSource<JavaClassSource> method : clazz.getMethods())
			for (final AnnotationSource<JavaClassSource> annotation : method
					.getAnnotations())
				if (annotation.getQualifiedName().startsWith(CUKES_IDENTIFIER))
					methods.add(method);
		return methods;
	}
}
