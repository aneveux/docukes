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
package com.github.aneveux.docukes;

/**
 * Just a simple class wrapper for our constants ;)
 *
 * @author aneveux
 * @version 1.0
 *
 */
public class Constants {

	/**
	 * Target directory in which documentation files should be generated
	 */
	public static final String TARGET_DIRECTORY = "/target/docukes";

	/**
	 * Pattern to be used in order to identify cukes annotations in the source
	 * code
	 */
	public static final String CUKES_IDENTIFIER = "cucumber.api.java";

}
