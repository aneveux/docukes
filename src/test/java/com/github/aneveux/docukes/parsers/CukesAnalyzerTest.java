package com.github.aneveux.docukes.parsers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.github.aneveux.docukes.parsers.TestResourcesHelper.TestClassesNames;

public class CukesAnalyzerTest {

	CukesAnalyzer analyzer;

	@Before
	public void setUp() {
		analyzer = new CukesAnalyzer();
	}

	@Test
	public void testIsCukes() {
		assertFalse("Non Cukes is detected as a Cukes",
				analyzer.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithoutCukes)));
		assertTrue("Cuke is detected as a non Cuke",
				analyzer.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithCukesAndDoc)));
		assertTrue("Cuke is detected as a non Cuke",
				analyzer.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithOnlyCukes)));
		assertTrue(
				"Cuke is detected as a non Cuke",
				analyzer.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithCukesAndDocAndStuff)));
		assertTrue("Cuke is detected as a non Cuke",
				analyzer.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithCukesAndStuff)));
	}

}
