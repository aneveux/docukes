package com.github.aneveux.docukes.parsers;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.aneveux.docukes.parsers.TestResourcesFinder.Resource;

/**
 * Unit tests for ResourceFinder
 *
 * @author aneveux
 * @version 1.0
 *
 */
public class ResourceFinderTest {

	/**
	 * Testing {@link ResourceFinder#explore(File, List, String)} method
	 */
	@Test
	public void testExploreTestResourcesContainingCorrectAmountOfFiles() {
		List<File> files = new ArrayList<File>();
		files = ResourceFinder.explore(
				TestResourcesFinder.getTestResourcesFolder(), files,
				TestResourcesFinder.JAVA_EXTENSION);
		assertEquals(
				"Test resources directory doesn't contain the correct amount of Java files",
				TestResourcesFinder.Resource.values().length, files.size());
	}

	/**
	 * Testing {@link ResourceFinder#explore(File, List, String)} method
	 */
	@Test
	public void testExploreTestResourcesFilteringJava() {
		List<File> files = new ArrayList<File>();
		files = ResourceFinder.explore(
				TestResourcesFinder.getTestResourcesFolder(), files,
				TestResourcesFinder.PHP_EXTENSION);
		assertEquals(
				"ClazzLocator isn't filtering properly resources from test resources directory",
				0, files.size());
	}

	/**
	 * Testing {@link ResourceFinder#explore(File, List, String)} method
	 */
	@Test
	public void testExploreTestResourcesContaningCorrectFiles() {
		List<File> files = new ArrayList<File>();
		files = ResourceFinder.explore(
				TestResourcesFinder.getTestResourcesFolder(), files,
				TestResourcesFinder.JAVA_EXTENSION);
		boolean found = false;
		for (final Resource name : TestResourcesFinder.Resource.values()) {
			for (final File file : files)
				found |= file.getName().equalsIgnoreCase(
						name.toString() + TestResourcesFinder.JAVA_EXTENSION);
			assertTrue(
					"Test resources directory doesn't contain one of the necessary files",
					found);
			found = false;
		}

	}
}
