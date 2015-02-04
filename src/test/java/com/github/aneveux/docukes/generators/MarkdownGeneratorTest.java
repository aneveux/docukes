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
import com.github.aneveux.docukes.parsers.TestResourcesHelper;
import com.github.aneveux.docukes.parsers.TestResourcesHelper.Resource;

public class MarkdownGeneratorTest {

	public MarkdownGenerator generator;

	@Before
	public void setUp() {
		generator = new MarkdownGenerator(Paths.get("").toFile());
	}

	@Test
	public void testWrite() {
		CukesParser parser = new CukesParser(
				TestResourcesHelper.getTestResource(Resource.WithCukesAndDoc,
						TestResourcesHelper.JAVA_EXTENSION));
		generator.write(parser.getClazz(), parser.getCukesMethods(), false);
		parser = new CukesParser(TestResourcesHelper.getTestResource(
				Resource.WithCukesAndDocAndStuff,
				TestResourcesHelper.JAVA_EXTENSION));
		generator.write(parser.getClazz(), parser.getCukesMethods(), false);
		parser = new CukesParser(TestResourcesHelper.getTestResource(
				Resource.WithCukesAndStuff, TestResourcesHelper.JAVA_EXTENSION));
		generator.write(parser.getClazz(), parser.getCukesMethods(), false);
		parser = new CukesParser(TestResourcesHelper.getTestResource(
				Resource.WithOnlyCukes, TestResourcesHelper.JAVA_EXTENSION));
		generator.write(parser.getClazz(), parser.getCukesMethods(), false);
	}

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

	@Test
	public void testGenerateContent() {
		final CukesParser parser = new CukesParser(
				TestResourcesHelper.getTestResource(Resource.WithCukesAndDoc,
						TestResourcesHelper.JAVA_EXTENSION));
		final File testResource = TestResourcesHelper.getTestResource(
				Resource.WithCukesAndDoc, TestResourcesHelper.MD_EXTENSION);
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
