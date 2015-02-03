package com.github.aneveux.docukes.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ClazzLocatorTest {

	public static final String JAVA_EXTENSION = ".java";
	public static final String PHP_EXTENSION = ".php";

	public static File testResources;

	public static String[] testClasses = { "WithCukesAndDoc",
			"WithCukesAndDocAndStuff", "WithCukesAndStuff", "WithOnlyCukes",
			"WithoutCukes" };

	@Before
	public void setUp() {
		final Path testResourcesPath = Paths.get("").resolve(
				"target/test-classes/docukes");
		testResources = testResourcesPath.toFile();
	}

	@Test
	public void testExploreTestResourcesContainingCorrectAmountOfFiles() {
		List<File> files = new ArrayList<File>();
		files = ClazzLocator.explore(testResources, files, JAVA_EXTENSION);
		assertEquals(
				"Test resources directory doesn't contain the correct amount of Java files",
				testClasses.length, files.size());
	}

	@Test
	public void testExploreTestResourcesFilteringJava() {
		List<File> files = new ArrayList<File>();
		files = ClazzLocator.explore(testResources, files, PHP_EXTENSION);
		assertEquals(
				"ClazzLocator isn't filtering properly resources from test resources directory",
				0, files.size());
	}

	@Test
	public void testExploreTestResourcesContaningCorrectFiles() {
		List<File> files = new ArrayList<File>();
		files = ClazzLocator.explore(testResources, files, JAVA_EXTENSION);
		boolean found = false;
		for (final String name : testClasses) {
			for (final File file : files)
				found |= file.getName().equalsIgnoreCase(name + JAVA_EXTENSION);
			assertTrue(
					"Test resources directory doesn't contain one of the necessary files",
					found);
			found = false;
		}

	}
}
