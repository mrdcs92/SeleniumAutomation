package components;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.testng.PickleWrapper;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		String testName = result.getMethod().getMethodName();
		Object[] parameters = result.getParameters();

		for (Object parameter : parameters) {

			// Cucumber scenario
			if (parameter instanceof PickleWrapper pickleWrapper) {
				testName = pickleWrapper.getPickle().getName();
			}

			// Normal TestNG DataProvider test
			else if (parameter instanceof Map<?, ?> testData) {
				Object product = testData.get("product");
				if (product != null) {
					testName = testName + " - " + product;
				}
			}
		}

		ExtentTest test = extent.createTest(testName);
		ExtentTestManager.setTest(test);

		// Log normal TestNG DataProvider values
		for (Object parameter : parameters) {

			if (parameter instanceof Map<?, ?> testData) {

				testData.forEach((key, value) -> {

					if (key.toString().equalsIgnoreCase("password")) {
						ExtentTestManager.getTest().info(key + ": ********");
					} else {
						ExtentTestManager.getTest().info(key + ": " + value);
					}
				});
			}
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
		ExtentTestManager.removeTest();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTestManager.getTest().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentTestManager.getTest().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		ExtentTestManager.removeTest();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		ExtentTestManager.removeTest();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
