package com.github.aneveux.docukes.parsers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Just a simple helper allowing to find test resources
 *
 * @author aneveux
 * @version 1.0
 *
 */
public class TestResourcesFinder {

	/**
	 * Java extension
	 */
	public static final String JAVA_EXTENSION = ".java";

	/**
	 * PHP extension
	 */
	public static final String PHP_EXTENSION = ".php";

	/**
	 * Markdown extension
	 */
	public static final String MD_EXTENSION = ".md";

	/**
	 * Folder in which test resources will be put
	 */
	private static File testResourcesFolder;

	/**
	 * Just an enum containing all the names of test resources to be used for
	 * the unit tests
	 *
	 * @author aneveux
	 *
	 */
	public enum Resource {
		WithCukesAndDoc, WithCukesAndDocAndStuff, WithCukesAndStuff, WithOnlyCukes, WithoutCukes, WithoutCukesAgain
	}

	/**
	 * Initiatilisation of the resources folder
	 */
	static {
		final Path testResourcesPath = Paths.get("").resolve(
				"target/test-classes/docukes");
		testResourcesFolder = testResourcesPath.toFile();
	}

	/**
	 * Gets the folder in which test resources are stored
	 *
	 * @return the folder in which test resources are located
	 */
	public static File getTestResourcesFolder() {
		return testResourcesFolder;
	}

	/**
	 * Returns a particular resource from the test resources folder
	 *
	 * @param name
	 *            the name of the resource you want to get
	 * @param extension
	 *            the extension of the file you need
	 * @return the File matching with your request
	 */
	public static File getTestResource(Resource name, String extension) {
		List<File> files = new ArrayList<File>();
		files = ResourceFinder.explore(testResourcesFolder, files, extension);
		for (final File f : files)
			if (f.getName().equalsIgnoreCase(name.toString() + extension))
				return f;
		return null;
	}

}
