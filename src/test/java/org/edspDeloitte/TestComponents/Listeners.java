package org.edspDeloitte.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.edspDeloitte.Resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentReports eReport =  ExtentReporterNG.getReportObject();
    ExtentTest test;
    //ThreadLocal<ExtentTest> extentTest = new ThreadLocal();
    @Override
    public void onTestStart(ITestResult result) {
        test = eReport.createTest(result.getMethod().getMethodName());
        //extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,"Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //extentTest.get().fail(result.getThrowable());
        test.fail(result.getThrowable());
        //take screenshot

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            //extentTest.get().addScreenCaptureFromPath(getScreenshot(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(getScreenshot(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        eReport.flush();
    }
}
