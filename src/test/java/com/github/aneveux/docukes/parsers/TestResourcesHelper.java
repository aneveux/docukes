package com.github.aneveux.docukes.parsers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestResourcesHelper {

	public static final String JAVA_EXTENSION = ".java";
	public static final String PHP_EXTENSION = ".php";
	public static final String MD_EXTENSION = ".md";

	private static File testResourcesFolder;

	public enum Resource {
		WithCukesAndDoc, WithCukesAndDocAndStuff, WithCukesAndStuff, WithOnlyCukes, WithoutCukes, WithoutCukesAgain
	}

	static {
		final Path testResourcesPath = Paths.get("").resolve(
				"target/test-classes/docukes");
		testResourcesFolder = testResourcesPath.toFile();
	}

	public static File getTestResourcesFolder() {
		return testResourcesFolder;
	}

	public static File getTestResource(Resource name, String extension) {
		List<File> files = new ArrayList<File>();
		files = ClazzLocator.explore(testResourcesFolder, files, extension);
		for (final File f : files)
			if (f.getName().equalsIgnoreCase(name.toString() + extension))
				return f;
		return null;
	}

}
