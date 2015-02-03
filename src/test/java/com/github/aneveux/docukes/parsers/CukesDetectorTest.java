package com.github.aneveux.docukes.parsers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aneveux.docukes.parsers.TestResourcesHelper.TestClassesNames;

public class CukesDetectorTest {

	@Test
	public void testIsCukes() {
		assertFalse("Non Cukes is detected as a Cukes",
				CukesDetector.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithoutCukes)));
		assertFalse("Non Cukes is detected as a Cukes",
				CukesDetector.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithoutCukesAgain)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithCukesAndDoc)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithOnlyCukes)));
		assertTrue(
				"Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithCukesAndDocAndStuff)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesHelper
						.getTestResource(TestClassesNames.WithCukesAndStuff)));
	}

}
