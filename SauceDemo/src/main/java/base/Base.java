package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Base 
{
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static Row row;
	public int r;
	public static ExtentHtmlReporter exthtml;
	public static ExtentTest exttest;
	public static ExtentReports report;
	public static Properties prop;

	/*To setup the initial environment for the reports creation and to provide path to properties file*/
	@BeforeSuite
	public void setUp() throws IOException, IOException
	{
		prop = new Properties();
		prop.load(new FileInputStream("./src/main/java/config/config.properties"));
		exthtml = new ExtentHtmlReporter(prop.getProperty("reports"));
		report = new ExtentReports();
		report.attachReporter(exthtml);
		report.setSystemInfo("Host Name", "TestSystem"); //name of the system
		report.setSystemInfo("Environment", "Test Env");
		report.setSystemInfo("User Name", "Alka");
		exthtml.config().setDocumentTitle("SauceDemo");
		exthtml.config().setReportName("SauceDemo Functional Testing");
		exthtml.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		exthtml.config().setTheme(Theme.DARK);
	}
	
	/*To initialize the browser through the driver object,maximize the window and implicit wait  30 sec */
	@BeforeTest
	@Parameters({"browser"})
	public void initialize(String browser)
	{
		if(browser.matches("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}
		
		if(browser.matches("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		}
	}

	/*To take screenshot of the page*/
	public void Img(String fName) throws IOException
	{
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("C:\\Java_prac\\SauceDemo\\TestData\\"+fName+".png"));
	}

	/*To take full screenshot of the page */
	public void fullPageImg(String fullImg) throws Exception 
	{
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(5000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(),"PNG",new File("C:\\Users\\adubey\\OneDrive - Planit Test Management Solutions Pty Ltd\\Desktop\\SauceDemo Project\\full_screenshots\\"+ fullImg +".png"));
	}

	/*To close the browser & create reports*/
	@AfterSuite
	public void teardown()
	{
		report.flush();
		driver.quit();
	}
}



