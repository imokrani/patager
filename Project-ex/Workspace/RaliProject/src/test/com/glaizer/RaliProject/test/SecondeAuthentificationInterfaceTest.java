package com.glaizer.RaliProject.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class SecondeAuthentificationInterfaceTest extends SeamTest {

	@Test
	public void test_secondeAuthentification() throws Exception {
		new FacesRequest("/secondeAuthentification.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{SecondeAuthentification.secondeAuthentification}");
			}
		}.run();
	}
}
