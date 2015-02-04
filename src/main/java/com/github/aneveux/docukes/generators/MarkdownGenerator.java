package com.github.aneveux.docukes.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public class MarkdownGenerator {

	public static final String TARGET_DIRECTORY = "/target/docukes";
	public static final String CUKES_ANNOTATION_IDENTIFIER = "cucumber.api.java";
	private static File target;

	protected static String template_body;
	protected static String template_stepdefs;

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

	protected String generateContent(JavaClassSource clazz,
			List<MethodSource<JavaClassSource>> methods, boolean withDate) {
		String res = "";
		res += generateBody(clazz, withDate);
		res += generateStepdefs(methods);
		return res;
	}

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

	protected String generateStepdefs(
			List<MethodSource<JavaClassSource>> methods) {
		String res = "";
		for (final MethodSource<JavaClassSource> method : methods)
			res += generateStepdef(method);
		return res;
	}

	protected String generateStepdef(MethodSource<JavaClassSource> method) {
		String res;
		res = template_stepdefs.replace("{method}",
				splitCamelCase(method.getName()));
		for (final AnnotationSource<JavaClassSource> annotation : method
				.getAnnotations())
			if (annotation.getQualifiedName().startsWith(
					CUKES_ANNOTATION_IDENTIFIER))
				res = res.replace("{annotation}", annotation.getName() + ": "
						+ annotation.getLiteralValue());
		if (method.hasJavaDoc())
			res = res.replace("{javadoc}", method.getJavaDoc().getText());
		return res;
	}

	public String splitCamelCase(String s) {
		return s.replaceAll(String.format("%s|%s|%s",
				"(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}
}
