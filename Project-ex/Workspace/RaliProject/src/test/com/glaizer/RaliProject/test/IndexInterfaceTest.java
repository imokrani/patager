package com.glaizer.RaliProject.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class IndexInterfaceTest extends SeamTest {

	@Test
	public void test_index() throws Exception {
		new FacesRequest("/index.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{Index.index}");
			}
		}.run();
	}
}
