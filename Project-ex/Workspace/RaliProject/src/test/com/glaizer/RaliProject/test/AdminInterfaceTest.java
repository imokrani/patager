package com.glaizer.RaliProject.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class AdminInterfaceTest extends SeamTest {

	@Test
	public void test_admin() throws Exception {
		new FacesRequest("/admin.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{Admin.admin}");
			}
		}.run();
	}
}
