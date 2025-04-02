package org.edspDeloitte.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExtentReporterNG {

    public static ExtentReports getReportObject(){
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf=new SimpleDateFormat("ddMMYYYY-hhmmss");
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/index"+sdf.format(cal.getTime())+".html");
    //set report configuration

        sparkReporter.config().setReportName("Web Automation Results");
        sparkReporter.config().setDocumentTitle("Test results");
    // create report object
        ExtentReports eReport= new ExtentReports();
        eReport.attachReporter(sparkReporter);
        eReport.setSystemInfo("Tester", "Edmundo Sainz");
        return eReport;
    }
}
