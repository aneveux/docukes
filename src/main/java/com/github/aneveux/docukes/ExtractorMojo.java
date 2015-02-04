package com.github.aneveux.docukes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.github.aneveux.docukes.generators.MarkdownGenerator;
import com.github.aneveux.docukes.parsers.ClazzLocator;
import com.github.aneveux.docukes.parsers.CukesDetector;
import com.github.aneveux.docukes.parsers.CukesParser;

@Mojo(name = "generate", threadSafe = true)
public class ExtractorMojo extends AbstractMojo {

	/**
	 * The base directory, in which to search for project files.
	 */
	@Parameter(property = "basedir", defaultValue = "${basedir}", required = true)
	public File basedir;

	public void execute() throws MojoExecutionException, MojoFailureException {
		System.out.println("Searching for classes...");

		List<File> classes = new ArrayList<File>();
		classes = ClazzLocator.explore(basedir, classes, ".java");

		System.out
				.println("Found " + classes.size() + " classes to analyze...");

		for (final File clazz : classes)
			if (CukesDetector.isCukes(clazz)) {
				System.out.println("Found Cuke: " + clazz.getName());
				final CukesParser parser = new CukesParser(clazz);
				final MarkdownGenerator generator = new MarkdownGenerator(
						basedir);
				generator.write(parser.getClazz(), parser.getCukesMethods(),
						true);
			}

		System.out.println("Documentation generated!");
	}

}
