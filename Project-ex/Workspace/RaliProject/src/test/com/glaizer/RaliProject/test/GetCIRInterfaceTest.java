package com.glaizer.RaliProject.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class GetCIRInterfaceTest extends SeamTest {

	@Test
	public void test_getCIR() throws Exception {
		new FacesRequest("/getCIR.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{GetCIR.getCIR}");
			}
		}.run();
	}
}
