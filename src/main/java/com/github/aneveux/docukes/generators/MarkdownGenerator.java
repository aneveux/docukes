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
package com.github.aneveux.docukes.generators;

import static com.github.aneveux.docukes.Constants.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

/**
 * This MarkdownGenerator allows to generate a markdown file containing the
 * documentation from a Java class containing some cukes annotations. The
 * Markdown generation is made using templates which are found in the resources
 * folder.
 *
 * @author aneveux
 * @version 1.0
 *
 */
public class MarkdownGenerator {

	/**
	 * Target folder in which the markdown files will be generated
	 */
	private static File target;

	/**
	 * Content of the markdown template to be used for the body of a class
	 */
	protected static String template_body;

	/**
	 * Content of the markdown template to be used for all the stepdefs which
	 * are found in a cukes class
	 */
	protected static String template_stepdefs;

	/**
	 * Allows to create a new instance of a MarkdownGenerator
	 * 
	 * @param basedir
	 *            the path in which the markdown files will be generated
	 */
	public MarkdownGenerator(File basedir) {
		target = new File(basedir.getAbsolutePath() + TARGET_DIRECTORY);
		if (!target.exists())
			target.mkdirs();
		try {
			template_body = IOUtils.toString(getClass().getResourceAsStream(
					"/template_body.md"));
			template_stepdefs = IOUtils.toString(getClass()
					.getResourceAsStream("/template_stepdefs.md"));
		} catch (final IOException e) {
			System.err.println("Cannot load templates...");
			e.printStackTrace();
		}
	}

	/**
	 * Creates a markdown file in the target repository containing some
	 * documentation generated from the specified Java class
	 * 
	 * @param clazz
	 *            a Java class containing some cukes annotations
	 * @param methods
	 *            the methods of the Java class which are actually annotated
	 *            with Cukes
	 * @param withDate
	 *            defines if the markdown file should contain the time on which
	 *            generation has been performed
	 */
	public void write(JavaClassSource clazz,
			List<MethodSource<JavaClassSource>> methods, boolean withDate) {
		final File file = new File(target.getPath() + "/" + clazz.getName()
				+ ".md");
		try {
			final FileWriter writer = new FileWriter(file);
			writer.write(generateContent(clazz, methods, withDate));
			writer.flush();
			writer.close();
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Generates the content of the whole markdown file for a Java class and its
	 * methods
	 * 
	 * @param clazz
	 *            a Java class containing some cukes annotations
	 * @param methods
	 *            the methods of the Java class which are actually annotated
	 *            with Cukes
	 * @param withDate
	 *            defines if the markdown file should contain the time on which
	 *            generation has been performed
	 * @return a String containing all the markdown to be written in the
	 *         generated file
	 */
	protected String generateContent(JavaClassSource clazz,
			List<MethodSource<JavaClassSource>> methods, boolean withDate) {
		String res = "";
		res += generateBody(clazz, withDate);
		res += generateStepdefs(methods);
		return res;
	}

	/**
	 * Generates the body of the markdown file, meaning the top part containing
	 * some global documentation
	 * 
	 * @param clazz
	 *            a Java class containing some cukes annotations
	 * @param withDate
	 *            defines if the markdown file should contain the time on which
	 *            generation has been performed
	 * @return a String containing all the markdown linked to the Java class,
	 *         without any information about the stepdefs
	 */
	protected String generateBody(JavaClassSource clazz, boolean withDate) {
		String res;
		res = template_body.replace("{title}", splitCamelCase(clazz.getName()));
		// Parsing Javadoc
		if (clazz.hasJavaDoc()) {
			// searching for author
			res = res.replace("{author}", clazz.getJavaDoc().getTagNames()
					.contains("@author") ? clazz.getJavaDoc()
							.getTags("@author").get(0).getValue() : "unknown");
			// searching for version
			res = res
					.replace(
							"{version}",
							clazz.getJavaDoc().getTagNames()
							.contains("@version") ? clazz.getJavaDoc()
									.getTags("@version").get(0).getValue()
									: "unknown");
			res = res.replace("{javadoc}", clazz.getJavaDoc().getText());
		}
		if (withDate)
			res = res.replace("{date}", new Date().toString());
		return res;
	}

	/**
	 * Generates the stepdefs of the markdown file, meaning the repetitive part
	 * linked to all stepdefs found in the java class
	 * 
	 * @param methods
	 *            the methods of the Java class which are actually annotated
	 *            with Cukes
	 * @return a String containing all the markdown related to all stepdefs
	 *         found in the Java class
	 */
	protected String generateStepdefs(
			List<MethodSource<JavaClassSource>> methods) {
		String res = "";
		for (final MethodSource<JavaClassSource> method : methods)
			res += generateStepdef(method);
		return res;
	}

	/**
	 * Generates a stepdef in markdown, giving information about the annotation,
	 * and the associated javadoc
	 * 
	 * @param method
	 *            a method of the Java class which is actually annotated with
	 *            Cukes
	 * @return a String containing all the markdown related to a stepdef
	 */
	protected String generateStepdef(MethodSource<JavaClassSource> method) {
		String res;
		res = template_stepdefs.replace("{method}",
				splitCamelCase(method.getName()));
		for (final AnnotationSource<JavaClassSource> annotation : method
				.getAnnotations())
			if (annotation.getQualifiedName().startsWith(
					CUKES_IDENTIFIER))
				res = res.replace("{annotation}", annotation.getName() + ": "
						+ annotation.getLiteralValue());
		if (method.hasJavaDoc())
			res = res.replace("{javadoc}", method.getJavaDoc().getText());
		return res;
	}

	/**
	 * Allows to convert camelcase to human readable names
	 *
	 * Thanks
	 * http://stackoverflow.com/questions/2559759/how-do-i-convert-camelcase
	 * -into-human-readable-names-in-java
	 * 
	 * @param s
	 *            a String in Camel Case
	 * @return a human readable String
	 */
	public String splitCamelCase(String s) {
		return s.replaceAll(String.format("%s|%s|%s",
				"(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}
}
