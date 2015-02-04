package com.github.aneveux.docukes.parsers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aneveux.docukes.parsers.TestResourcesHelper.Resource;

public class CukesDetectorTest {

	@Test
	public void testIsCukes() {
		assertFalse("Non Cukes is detected as a Cukes",
				CukesDetector.isCukes(TestResourcesHelper.getTestResource(
						Resource.WithoutCukes,
						TestResourcesHelper.JAVA_EXTENSION)));
		assertFalse("Non Cukes is detected as a Cukes",
				CukesDetector.isCukes(TestResourcesHelper.getTestResource(
						Resource.WithoutCukesAgain,
						TestResourcesHelper.JAVA_EXTENSION)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesHelper.getTestResource(
						Resource.WithCukesAndDoc,
						TestResourcesHelper.JAVA_EXTENSION)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesHelper.getTestResource(
						Resource.WithOnlyCukes,
						TestResourcesHelper.JAVA_EXTENSION)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesHelper.getTestResource(
						Resource.WithCukesAndDocAndStuff,
						TestResourcesHelper.JAVA_EXTENSION)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesHelper.getTestResource(
						Resource.WithCukesAndStuff,
						TestResourcesHelper.JAVA_EXTENSION)));
	}

}
