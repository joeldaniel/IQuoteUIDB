package utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.ITestAnnotation;

import base.Testbase;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;


public class ListenerUtils extends TestListenerAdapter implements IRetryAnalyzer, IAnnotationTransformer{
	int reTrycount=0 ;
	int reTryLimit ;
	public static Logger Log = Logger.getLogger("devpinoyLogger");
	public String messageBody;

	


	public void onTestStart(ITestResult result) {

		System.out.println("****************************************************************************************");
		System.out.println("****************************************************************************************");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$       " + "Test Case started: " + result.getName()
				+ "    $$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("X");
		System.out.println("X");
	}

	@Step("Test Passed. Screenshot is Attached:")
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("X");
		System.out.println("X");
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXX    " + "TEST CASE PASSED:" + result.getName() + "XXXXXXXXXXXXXXXXXXXXXX");
		takeScreenShot(result.getName());
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("****************************************************************************************");
		System.out.println("****************************************************************************************");
	}

	@Step("Test failed")
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("X");
		System.out.println("X");
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXX    " + "TEST CASE FAILED:" + result.getName() + "    XXXXXXXXXXXXXXXXXXXXXX");
		//takeScreenShot(result.getName());
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("****************************************************************************************");
		System.out.println("****************************************************************************************");
	}

	@Step("Test Skipped. Please take a look at the attachments:")
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("X");
		System.out.println("X");
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXX    " + "TEST CASE SKIPPED:" + result.getName() + "    XXXXXXXXXXXXXXXXXXXXXX");
		takeScreenShot(result.getName());
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("****************************************************************************************");
		System.out.println("****************************************************************************************");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		Log.info("Number of Passed Tests: " + context.getPassedTests().getAllMethods().size());
		Log.info("Failed Tests: " + context.getFailedTests().getAllMethods());
		Log.info("Skipped Tests: " + context.getSkippedTests().getAllMethods());
		
	}

	@Attachment(value = "Screenshot of {0} ", type = "image/png")
	private byte[] takeScreenShot(String methodName) {

		Log.info("SCREENSHOT of the following test is taken: " + methodName);
		Log.info("----- ***** -----\n");
		return ((TakesScreenshot) Testbase.getdriver()).getScreenshotAs(OutputType.BYTES);
	}

	
	
	@Override
	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) {
		// TODO Auto-generated method stub
		IRetryAnalyzer retry = arg0.getRetryAnalyzer();

		if (retry == null)	{
		arg0.setRetryAnalyzer(ListenerUtils.class);
		}
	}

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
