package com.glaizer.RaliProject.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class LogoutInterfaceTest extends SeamTest {

	@Test
	public void test_logout() throws Exception {
		new FacesRequest("/logout.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{Logout.logout}");
			}
		}.run();
	}
}
