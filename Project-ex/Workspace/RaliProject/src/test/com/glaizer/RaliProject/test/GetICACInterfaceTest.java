package com.glaizer.RaliProject.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class GetICACInterfaceTest extends SeamTest {

	@Test
	public void test_getICAC() throws Exception {
		new FacesRequest("/getICAC.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{GetICAC.getICAC}");
			}
		}.run();
	}
}
