package com.github.aneveux.docukes.parsers;

import org.junit.Test;

import static org.junit.Assert.*;

import com.github.aneveux.docukes.parsers.TestResourcesHelper.Resource;

public class CukesParserTest {

	@Test
	public void testGetCukesMethodsCorrectAmountOfMethods() {
		CukesParser parser = new CukesParser(
				TestResourcesHelper.getTestResource(Resource.WithoutCukes,
						TestResourcesHelper.JAVA_EXTENSION));
		assertEquals(
				"Cukes methods shouldn't be detected in a non Cukes class", 0,
				parser.getCukesMethods().size());
		parser = new CukesParser(TestResourcesHelper.getTestResource(
				Resource.WithoutCukesAgain, TestResourcesHelper.JAVA_EXTENSION));
		assertEquals(
				"Cukes methods shouldn't be detected in a non Cukes class", 0,
				parser.getCukesMethods().size());
		parser = new CukesParser(TestResourcesHelper.getTestResource(
				Resource.WithCukesAndDoc, TestResourcesHelper.JAVA_EXTENSION));
		assertEquals("Cukes methods should be detected in a Cukes class", 3,
				parser.getCukesMethods().size());
		parser = new CukesParser(TestResourcesHelper.getTestResource(
				Resource.WithCukesAndDocAndStuff,
				TestResourcesHelper.JAVA_EXTENSION));
		assertEquals("Cukes methods should be detected in a Cukes class", 3,
				parser.getCukesMethods().size());
		parser = new CukesParser(TestResourcesHelper.getTestResource(
				Resource.WithCukesAndStuff, TestResourcesHelper.JAVA_EXTENSION));
		assertEquals("Cukes methods should be detected in a Cukes class", 6,
				parser.getCukesMethods().size());
		parser = new CukesParser(TestResourcesHelper.getTestResource(
				Resource.WithOnlyCukes, TestResourcesHelper.JAVA_EXTENSION));
		assertEquals("Cukes methods should be detected in a Cukes class", 6,
				parser.getCukesMethods().size());
	}

}
