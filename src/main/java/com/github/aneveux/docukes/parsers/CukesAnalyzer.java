package com.github.aneveux.docukes.parsers;

import java.io.File;
import java.io.FileNotFoundException;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.Import;
import org.jboss.forge.roaster.model.source.JavaClassSource;

public class CukesAnalyzer {

	public static final String CUKES_IMPORT_IDENTIFIER = "cucumber.api.java";

	public boolean isCukes(File javaClazz) {
		JavaClassSource clazz;
		try {
			clazz = Roaster.parse(JavaClassSource.class, javaClazz);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		// We consider a class is actually containing cucumber stepdefs if it
		// uses some cucumber related imports...
		// Could be improved, but that'll do the trick for now.
		for (final Import importz : clazz.getImports())
			if (importz.getPackage().startsWith(CUKES_IMPORT_IDENTIFIER))
				return true;
		return false;
	}
}
