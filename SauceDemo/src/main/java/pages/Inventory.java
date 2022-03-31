package pages;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import objects.Elements;
import reusables.Interactions;

public class Inventory extends HomePage
{
	/*To sort the product's price is ascending order(Low-to-high)*/
	public void sorting() throws Exception
	{	
		Interactions.drop_down(driver.findElement(Elements.options), prop.getProperty("price_dropdown"));
		Thread.sleep(3000);
		List<WebElement> SelectItem= driver.findElements(Elements.prod_nms);
		List<WebElement> ItemPrice = driver.findElements(Elements.prices);

		exttest=report.createTest("List is sorted");
		for(int i=0;i<SelectItem.size();i++)
		{
			exttest.log(Status.INFO, SelectItem.get(i).getText() + "       " + ItemPrice.get(i).getText());
		}
		//To take the screenshot of the sorted products
		Img("sorted");
	}

	/*To add 3 items into the cart*/
	public void selectItem() throws Exception
	{
		Thread.sleep(2000);
		List<WebElement> items = driver.findElements(Elements.addTocart);

		for(int i=0;i<3;i++)
		{
			items.get(i).click();
			Thread.sleep(2000);
		}
	}

	/*To validate that the selected 3 items are added into the cart*/
	public void shoppingCart() throws Exception
	{
		Thread.sleep(3000);
		Interactions.click_button(driver.findElement(Elements.shoppingCart));
		List<WebElement> SelectItem= driver.findElements(Elements.prod_nms);
		List<WebElement> ItemPrice = driver.findElements(Elements.prices);
		//to create a test that shows the list of added products
		exttest=report.createTest("3 items added to cart");
		for(int i=0;i<SelectItem.size();i++)
		{
			exttest.log(Status.INFO, SelectItem.get(i).getText() + "       " + ItemPrice.get(i).getText());
		}
		//to take the screenshot of added items in the cart
		Img("added");
	}

	/*to remove 1 product from the cart and validate that 1 product is lesser than earlier*/
	public void removeItem() throws Exception
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Elements.prod_nms));
		Interactions.click_button(driver.findElement(Elements.removeItem));

		List<WebElement> SelectItem= driver.findElements(Elements.prod_nms);
		List<WebElement> ItemPrice = driver.findElements(Elements.prices);

		//to create a test that shows the remaining products in the cart
		exttest=report.createTest("1 item removed from cart");
		for(int i=0;i<SelectItem.size();i++)
		{
			exttest.log(Status.INFO, SelectItem.get(i).getText() + "       " + ItemPrice.get(i).getText()); 
		}
		//to take the screenshot of the updated cart
		Img("ss_updated");
	}

	/*to create 3 sheets for sorted,added and remaining items in the cart*/
	public void file(String selectSht) throws Exception
	{
		FileInputStream fin=new FileInputStream("./TestData/Sauce_Items.xlsx");
		wb=new XSSFWorkbook(fin);
		ws=wb.getSheet(selectSht);
		Row row;

		List<WebElement> Product_names=driver.findElements(Elements.prod_nms);
		List<WebElement> Prices=driver.findElements(Elements.prices);
		for(int i=0;i<Product_names.size();i++)
		{
			row=ws.createRow(i);
			row.createCell(0).setCellValue(Product_names.get(i).getText());
			row.createCell(1).setCellValue(Prices.get(i).getText());
		}
		FileOutputStream fout=new FileOutputStream("./TestData/Sauce_Items.xlsx");
		wb.write(fout);
	}

}