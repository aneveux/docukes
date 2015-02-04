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
