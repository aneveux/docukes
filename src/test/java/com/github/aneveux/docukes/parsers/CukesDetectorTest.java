package com.github.aneveux.docukes.parsers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.aneveux.docukes.parsers.TestResourcesFinder.Resource;

/**
 * Unit tests for CukesDetector
 *
 * @author aneveux
 * @version 1.0
 *
 */
public class CukesDetectorTest {

	/**
	 * Testing {@link CukesDetector#isCukes(java.io.File)} method
	 */
	@Test
	public void testIsCukes() {
		assertFalse("Non Cukes is detected as a Cukes",
				CukesDetector.isCukes(TestResourcesFinder.getTestResource(
						Resource.WithoutCukes,
						TestResourcesFinder.JAVA_EXTENSION)));
		assertFalse("Non Cukes is detected as a Cukes",
				CukesDetector.isCukes(TestResourcesFinder.getTestResource(
						Resource.WithoutCukesAgain,
						TestResourcesFinder.JAVA_EXTENSION)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesFinder.getTestResource(
						Resource.WithCukesAndDoc,
						TestResourcesFinder.JAVA_EXTENSION)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesFinder.getTestResource(
						Resource.WithOnlyCukes,
						TestResourcesFinder.JAVA_EXTENSION)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesFinder.getTestResource(
						Resource.WithCukesAndDocAndStuff,
						TestResourcesFinder.JAVA_EXTENSION)));
		assertTrue("Cuke is detected as a non Cuke",
				CukesDetector.isCukes(TestResourcesFinder.getTestResource(
						Resource.WithCukesAndStuff,
						TestResourcesFinder.JAVA_EXTENSION)));
	}

}
