package pages;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import com.aventstack.extentreports.Status;

import base.Base;
import objects.Elements;
import reusables.Interactions;

public class HomePage extends Base {

	/*To check if the url is stable and working*/
	public void checkUrl() throws Exception
	{
		URL urlc = new URL(prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		HttpURLConnection checkUrl=(HttpURLConnection)urlc.openConnection();
		checkUrl.setConnectTimeout(5000);
		checkUrl.setRequestMethod("HEAD");
		checkUrl.connect();
		if(checkUrl.getResponseCode()>=400)
		{
			System.exit(0);
		}
		else
		{
			exttest=report.createTest("URL working");
			System.out.println("URL is working.");
		}
	}

	/*To validate title using soft asserts*/
	public void getTitle()
	{
		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Swag Labs";
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		exttest=report.createTest("Title");
		exttest.log(Status.INFO, ActualTitle);
		System.out.println(ActualTitle);
	}

	/*To login with set of invalid and valid inputs*/
	public void login() throws Exception
	{
		FileInputStream fis = new FileInputStream("./TestData/SauceDemoDDT.xlsx");
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet ws= wb.getSheet("LoginCred");
		Row row;
		//getting the input from excel sheet
		for(int r=1; r<=ws.getLastRowNum(); r++)
		{
			row=ws.getRow(r);
			driver.get(prop.getProperty("url"));
			Interactions.writeText(driver.findElement(Elements.username),(row.getCell(0).getStringCellValue()));
			Interactions.writeText(driver.findElement(Elements.password), (row.getCell(1).getStringCellValue()));
			Interactions.click_button(driver.findElement(Elements.login));

			try
			{
				//to check if the error message element is displayed
				if(driver.findElement(Elements.err_msg).isDisplayed())
				{
					String str=driver.findElement(Elements.err_msg).getText();
					if(str.contains("Username and password do not match"))
						row.createCell(2).setCellValue(str);
					Img("Error message");
					Thread.sleep(2000);
					driver.navigate().back();
					Thread.sleep(3000);
				}
				//to update the success of logging in into the excel sheet
			}catch(NoSuchElementException e)
			{
				row.createCell(2).setCellValue("success!");
			}
			FileOutputStream fos= new FileOutputStream("./TestData/SauceDemoDDT.xlsx");
			wb.write(fos);
		}
	}
}