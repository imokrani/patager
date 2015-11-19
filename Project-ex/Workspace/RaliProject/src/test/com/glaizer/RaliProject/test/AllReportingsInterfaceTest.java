package com.glaizer.RaliProject.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class AllReportingsInterfaceTest extends SeamTest {

	@Test
	public void test_allReportings() throws Exception {
		new FacesRequest("/allReportings.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{AllReportings.allReportings}");
			}
		}.run();
	}
}
