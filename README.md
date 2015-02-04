#Docukes
[![Build Status](https://travis-ci.org/aneveux/docukes.svg)](https://travis-ci.org/aneveux/docukes)

##About
Docukes is a simple Maven plugin allowing to generate some Markdown documentation for your cucumber-jvm frameworks.

The goal behind it is simply to parse your Java source code, searching for Cucumber annotations in the Java classes, and extract some information out of the annotations and the Javadoc in order to generate some Mardown files that you can use for Github wikis or Github pages in order to document your framework.

##Documentation
There's no documentation yet since it's really easy ;) I'll add some if needed/required.

Just add this dependency in the `<build></build>` section of your `pom.xml`:

```xml
			<plugin>
				<groupId>com.github.aneveux</groupId>
				<artifactId>docukes</artifactId>
				<version>1.0.1</version>
			</plugin>
```

Then, you can execute the documentation generation with this simple command line: `mvn clean docukes:generate`

And all Markdown files will be generated under the `target/docukes` directory.

##Usage
Docukes is delivered on its own Maven repository that you can add in your pom.xml:

```xml
		<pluginRepository>
			<id>docukes</id>
			<url>https://raw.github.com/aneveux/docukes/mvn-repo/</url>
			<snapshots>
				<enabled>true</enabled>
				<updatePolicy>always</updatePolicy>
			</snapshots>
		</pluginRepository>
```

##Contribution
All [feedbacks](https://github.com/aneveux/docukes/issues) are welcome.

If you'd like to contribute, don't hesitate to [fork](https://github.com/aneveux/docukes/fork) & pullrequest ;)
