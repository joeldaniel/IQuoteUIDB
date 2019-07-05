package testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Testbase;

public class TestingReport extends Testbase {
	@Test
	public void testingreport() {
		test = extent.createTest("testingreport");
		test.log(Status.PASS, "the comment need to be entered here");
	}
}
