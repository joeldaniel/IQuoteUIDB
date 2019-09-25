package utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.ITestAnnotation;


public class ListenerUtils extends TestListenerAdapter implements IRetryAnalyzer, IAnnotationTransformer{

	int reTrycount=0 ;
	int reTryLimit ;
	
	/*public ListenerUtils() {
		Log.setLogger("ListenerUtils");
	}*/


	public void onTestStart(ITestResult result) {

		System.out.println("****************************************************************************************");
		System.out.println("****************************************************************************************");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$       " + "Test Case started: " + result.getName()
				+ "    $$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("X");
		System.out.println("X");
	}

	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("X");
		System.out.println("X");
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXX    " + "TEST CASE PASSED:" + result.getName() + "XXXXXXXXXXXXXXXXXXXXXX");
		//takeScreenShot(result.getName());
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXXX             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("****************************************************************************************");
		System.out.println("****************************************************************************************");
	}

	
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

	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("X");
		System.out.println("X");
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXX    " + "TEST CASE SKIPPED:" + result.getName() + "    XXXXXXXXXXXXXXXXXXXXXX");
		//takeScreenShot(result.getName());
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
		/*Log.info("Number of Passed Tests: " + context.getPassedTests().getAllMethods().size());
		Log.info("Failed Tests: " + context.getFailedTests().getAllMethods());
		Log.info("Skipped Tests: " + context.getSkippedTests().getAllMethods());*/
	}

	
	/*private byte[] takeScreenShot(String methodName) {

		
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}*/

	
	
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
