package org.edspDeloitte.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.edspDeloitte.PageObjects.LandingPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BaseTest {


    int count = 1;
    public WebDriver driver;
    public LandingPage lp;
    private void initilizeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/edspDeloitte/Resources/GlobalData.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
        if(browserName.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options  = new ChromeOptions();
            if(browserName.contains("headless"))options.addArguments("headless");
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//full screen

            //headless mode


        } else if(browserName.contains("firefox")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if(browserName.contains("edge")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public List<HashMap<String, String>> getJasonDataToMap(String filePath) throws IOException {
        //Read jason file to string
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        //String to hasmap Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

    public String getScreenshot(String TCName,WebDriver d) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("ddMMYYYY-hhmmss");
        File file = new File(System.getProperty("user.dir")+"//reports//"+TCName+sdf.format(cal.getTime())+".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//"+TCName+sdf.format(cal.getTime())+".png";
    }


    @BeforeMethod (alwaysRun = true)
    public void launchApplication() throws IOException {
        initilizeDriver();
        //Landing page
        lp = new LandingPage(driver);
        lp.goTo();
    }
    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
