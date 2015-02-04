package com.github.aneveux.docukes.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

public class CukesParser {

	protected JavaClassSource clazz;
	public static final String CUKES_ANNOTATION_IDENTIFIER = "cucumber.api.java";

	public CukesParser(File javaClazz) {
		try {
			clazz = Roaster.parse(JavaClassSource.class, javaClazz);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public JavaClassSource getClazz() {
		return clazz;
	}

	public List<MethodSource<JavaClassSource>> getCukesMethods() {
		final List<MethodSource<JavaClassSource>> methods = new ArrayList<MethodSource<JavaClassSource>>();
		// Solution using loops and not direct types of annotations because of
		// i18n in cukes annotations... Would lead to too many choices ;)
		for (final MethodSource<JavaClassSource> method : clazz.getMethods())
			for (final AnnotationSource<JavaClassSource> annotation : method
					.getAnnotations())
				if (annotation.getQualifiedName().startsWith(
						CUKES_ANNOTATION_IDENTIFIER))
					methods.add(method);
		return methods;
	}
}
