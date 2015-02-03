package com.github.aneveux.docukes.parsers;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.aneveux.docukes.parsers.TestResourcesHelper.TestClassesNames;

public class ClazzLocatorTest {

	@Test
	public void testExploreTestResourcesContainingCorrectAmountOfFiles() {
		List<File> files = new ArrayList<File>();
		files = ClazzLocator.explore(
				TestResourcesHelper.getTestResourcesFolder(), files,
				TestResourcesHelper.JAVA_EXTENSION);
		assertEquals(
				"Test resources directory doesn't contain the correct amount of Java files",
				TestResourcesHelper.TestClassesNames.values().length,
				files.size());
	}

	@Test
	public void testExploreTestResourcesFilteringJava() {
		List<File> files = new ArrayList<File>();
		files = ClazzLocator.explore(
				TestResourcesHelper.getTestResourcesFolder(), files,
				TestResourcesHelper.PHP_EXTENSION);
		assertEquals(
				"ClazzLocator isn't filtering properly resources from test resources directory",
				0, files.size());
	}

	@Test
	public void testExploreTestResourcesContaningCorrectFiles() {
		List<File> files = new ArrayList<File>();
		files = ClazzLocator.explore(
				TestResourcesHelper.getTestResourcesFolder(), files,
				TestResourcesHelper.JAVA_EXTENSION);
		boolean found = false;
		for (final TestClassesNames name : TestResourcesHelper.TestClassesNames
				.values()) {
			for (final File file : files)
				found |= file.getName().equalsIgnoreCase(
						name.toString() + TestResourcesHelper.JAVA_EXTENSION);
			assertTrue(
					"Test resources directory doesn't contain one of the necessary files",
					found);
			found = false;
		}

	}
}
