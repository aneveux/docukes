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
package com.github.aneveux.docukes.generators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.github.aneveux.docukes.parsers.CukesParser;
import com.github.aneveux.docukes.parsers.TestResourcesFinder;
import com.github.aneveux.docukes.parsers.TestResourcesFinder.Resource;

/**
 * Unit tests for MarkdownGenerator
 *
 * @author aneveux
 * @version 1.0
 *
 */
public class MarkdownGeneratorTest {

	/**
	 * Instance of a MarkdownGenerator to be tested
	 */
	public MarkdownGenerator generator;

	/**
	 * Allows to setup the generator
	 */
	@Before
	public void setUp() {
		generator = new MarkdownGenerator(Paths.get("").toFile());
	}

	/**
	 * Testing the
	 * {@link MarkdownGenerator#write(org.jboss.forge.roaster.model.source.JavaClassSource, java.util.List, boolean)}
	 * method
	 *
	 * Please note the test is... actually not a test. I'll (probably) fix that
	 * later.
	 */
	@Test
	public void testWrite() {
		CukesParser parser = new CukesParser(
				TestResourcesFinder.getTestResource(Resource.WithCukesAndDoc,
						TestResourcesFinder.JAVA_EXTENSION));
		generator.write(parser.getClazz(), parser.getCukesMethods(), false);
		parser = new CukesParser(TestResourcesFinder.getTestResource(
				Resource.WithCukesAndDocAndStuff,
				TestResourcesFinder.JAVA_EXTENSION));
		generator.write(parser.getClazz(), parser.getCukesMethods(), false);
		parser = new CukesParser(TestResourcesFinder.getTestResource(
				Resource.WithCukesAndStuff, TestResourcesFinder.JAVA_EXTENSION));
		generator.write(parser.getClazz(), parser.getCukesMethods(), false);
		parser = new CukesParser(TestResourcesFinder.getTestResource(
				Resource.WithOnlyCukes, TestResourcesFinder.JAVA_EXTENSION));
		generator.write(parser.getClazz(), parser.getCukesMethods(), false);
	}

	/**
	 * Testing the {@link MarkdownGenerator#splitCamelCase(String)} method
	 */
	@Test
	public void testSplitCamelCase() {
		assertEquals("Split Camel Case isn't as expected", "A Perfect Robot",
				generator.splitCamelCase("APerfectRobot"));
		assertEquals("Split Camel Case isn't as expected", "Something Strange",
				generator.splitCamelCase("SomethingStrange"));
		assertEquals("Split Camel Case isn't as expected",
				"An XML Parser Doing Stuff",
				generator.splitCamelCase("AnXMLParserDoingStuff"));
		assertEquals("Split Camel Case isn't as expected", "Something",
				generator.splitCamelCase("Something"));
	}

	/**
	 * Testing the
	 * {@link MarkdownGenerator#generateContent(org.jboss.forge.roaster.model.source.JavaClassSource, java.util.List, boolean)}
	 * method
	 */
	@Test
	public void testGenerateContent() {
		final CukesParser parser = new CukesParser(
				TestResourcesFinder.getTestResource(Resource.WithCukesAndDoc,
						TestResourcesFinder.JAVA_EXTENSION));
		final File testResource = TestResourcesFinder.getTestResource(
				Resource.WithCukesAndDoc, TestResourcesFinder.MD_EXTENSION);
		String expected = "";
		try {
			final byte[] encoded = Files.readAllBytes(Paths.get(testResource
					.getPath()));
			expected = new String(encoded);
		} catch (final IOException e) {
			fail("Cannot read test resources");
			e.printStackTrace();
		}
		assertEquals(
				"Generated markdown isn't as expected",
				expected,
				generator.generateContent(parser.getClazz(),
						parser.getCukesMethods(), false));
	}
}
