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

import org.junit.Test;

import static org.junit.Assert.*;

import com.github.aneveux.docukes.parsers.TestResourcesFinder.Resource;

/**
 * Unit tests for CukesParser
 *
 * @author aneveux
 * @version 1.0
 *
 */
public class CukesParserTest {

	/**
	 * Testing {@link CukesParser#getCukesMethods()} method
	 */
	@Test
	public void testGetCukesMethodsCorrectAmountOfMethods() {
		CukesParser parser = new CukesParser(
				TestResourcesFinder.getTestResource(Resource.WithoutCukes,
						TestResourcesFinder.JAVA_EXTENSION));
		assertEquals(
				"Cukes methods shouldn't be detected in a non Cukes class", 0,
				parser.getCukesMethods().size());
		parser = new CukesParser(TestResourcesFinder.getTestResource(
				Resource.WithoutCukesAgain, TestResourcesFinder.JAVA_EXTENSION));
		assertEquals(
				"Cukes methods shouldn't be detected in a non Cukes class", 0,
				parser.getCukesMethods().size());
		parser = new CukesParser(TestResourcesFinder.getTestResource(
				Resource.WithCukesAndDoc, TestResourcesFinder.JAVA_EXTENSION));
		assertEquals("Cukes methods should be detected in a Cukes class", 3,
				parser.getCukesMethods().size());
		parser = new CukesParser(TestResourcesFinder.getTestResource(
				Resource.WithCukesAndDocAndStuff,
				TestResourcesFinder.JAVA_EXTENSION));
		assertEquals("Cukes methods should be detected in a Cukes class", 3,
				parser.getCukesMethods().size());
		parser = new CukesParser(TestResourcesFinder.getTestResource(
				Resource.WithCukesAndStuff, TestResourcesFinder.JAVA_EXTENSION));
		assertEquals("Cukes methods should be detected in a Cukes class", 6,
				parser.getCukesMethods().size());
		parser = new CukesParser(TestResourcesFinder.getTestResource(
				Resource.WithOnlyCukes, TestResourcesFinder.JAVA_EXTENSION));
		assertEquals("Cukes methods should be detected in a Cukes class", 6,
				parser.getCukesMethods().size());
	}

}
