package com.github.aneveux.docukes.parsers;

import org.junit.Test;

import static org.junit.Assert.*;

import com.github.aneveux.docukes.parsers.TestResourcesHelper.TestClassesNames;

public class CukesParserTest {

	@Test
	public void testGetCukesMethodsCorrectAmountOfMethods() {
		CukesParser parser = new CukesParser(
				TestResourcesHelper
				.getTestResource(TestClassesNames.WithoutCukes));
		assertEquals(
				"Cukes methods shouldn't be detected in a non Cukes class", 0,
				parser.getCukesMethods().size());
		parser = new CukesParser(
				TestResourcesHelper
				.getTestResource(TestClassesNames.WithoutCukesAgain));
		assertEquals(
				"Cukes methods shouldn't be detected in a non Cukes class", 0,
				parser.getCukesMethods().size());
		parser = new CukesParser(
				TestResourcesHelper
				.getTestResource(TestClassesNames.WithCukesAndDoc));
		assertEquals("Cukes methods should be detected in a Cukes class", 3,
				parser.getCukesMethods().size());
		parser = new CukesParser(
				TestResourcesHelper
				.getTestResource(TestClassesNames.WithCukesAndDocAndStuff));
		assertEquals("Cukes methods should be detected in a Cukes class", 3,
				parser.getCukesMethods().size());
		parser = new CukesParser(
				TestResourcesHelper
				.getTestResource(TestClassesNames.WithCukesAndStuff));
		assertEquals("Cukes methods should be detected in a Cukes class", 6,
				parser.getCukesMethods().size());
		parser = new CukesParser(
				TestResourcesHelper
				.getTestResource(TestClassesNames.WithOnlyCukes));
		assertEquals("Cukes methods should be detected in a Cukes class", 6,
				parser.getCukesMethods().size());
	}

}
