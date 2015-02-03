package com.github.aneveux.docukes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;

@Mojo(name = "extract", threadSafe = true)
public class ExtractorMojo extends AbstractMojo {

	/**
	 * The base directory, in which to search for project files.
	 */
	@Parameter(property = "basedir", defaultValue = "${basedir}", required = true)
	public File basedir;

	public void execute() throws MojoExecutionException, MojoFailureException {
		System.out.println("Yoooo!");
		System.out.println(basedir.toString());
		List<File> files = new ArrayList<File>();
		files = explore(basedir, files, "java");
		System.out.println("size:" + files.size());
		for (final File f : files) {
			System.out.println("file: " + f.toString());
			try {
				System.out.println("parsing:");
				final JavaClassSource clazz = Roaster.parse(
						JavaClassSource.class, f);
				System.out.println(clazz.getCanonicalName());

				for (final AnnotationSource<JavaClassSource> annotation : clazz
						.getAnnotations()) {
					System.out.println("annotation:");
					System.out.println(annotation.getQualifiedName());
				}
			} catch (final FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public List<File> explore(File path, List<File> files,
			String extensionFilter) {
		if (!path.isDirectory()) {
			if (path.isFile() && path.getName().endsWith(extensionFilter))
				files.add(path);
			return files;
		} else {
			for (final File children : path.listFiles())
				explore(children, files, extensionFilter);
			return files;
		}
	}
}
