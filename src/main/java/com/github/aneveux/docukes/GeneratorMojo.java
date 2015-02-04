/*
 * Docukes is a simple framework allowing to generate documentation out of cucumber java classes.
 * Copyright (C) 2015 Antoine Neveux or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
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
import com.github.aneveux.docukes.parsers.ResourceFinder;
import com.github.aneveux.docukes.parsers.CukesDetector;
import com.github.aneveux.docukes.parsers.CukesParser;

/**
 * This GeneratorMojo allows from a Maven plugin to parse the source code of the
 * project in which it's executed, searching for cucumber java classes, and will
 * then generate markdown documentation out of the annotations and javadoc that
 * is found in the code.
 *
 * @author aneveux
 * @version 1.0
 */
@Mojo(name = "generate", threadSafe = true)
public class GeneratorMojo extends AbstractMojo {

	/**
	 * The base directory, in which to search for project files.
	 */
	@Parameter(property = "basedir", defaultValue = "${basedir}", required = true)
	public File basedir;

	/**
	 * This method is called while executing the plugin and contains the actual
	 * implementation of its behavior. It'll allow to do all the parsing and
	 * markdown generation
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Searching for classes...");

		List<File> classes = new ArrayList<File>();
		classes = ResourceFinder.explore(basedir, classes, ".java");

		getLog().info("Found " + classes.size() + " classes to analyze...");

		for (final File clazz : classes)
			if (CukesDetector.isCukes(clazz)) {
				getLog().info("Found Cuke: " + clazz.getName());
				final CukesParser parser = new CukesParser(clazz);
				final MarkdownGenerator generator = new MarkdownGenerator(
						basedir);
				generator.write(parser.getClazz(), parser.getCukesMethods(),
						true);
			}

		getLog().info("Documentation generated!");
	}

}
