package com.glaizer.RaliProject.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class MyReportingInterfaceTest extends SeamTest {

	@Test
	public void test_myReporting() throws Exception {
		new FacesRequest("/myReporting.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{MyReporting.myReporting}");
			}
		}.run();
	}
}
