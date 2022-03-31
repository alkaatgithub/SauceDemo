package reusables;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Interactions 
{
	/*to take passed inputs in the input/text box(web element)*/
	public static void writeText(WebElement inputbox,String inputvalue)
	{
		if(inputbox.isDisplayed() && inputbox.isEnabled())
		{
			inputbox.sendKeys(inputvalue);
		}
	}
	/*to click on the button*/
	public static void click_button(WebElement button)
	{
		if(button.isDisplayed() && button.isEnabled())
		{
			button.click();
		}
	}
	/*to select a value in the drop-down list*/
	public static void drop_down(WebElement drop_lst, String value) 
	{
		if(drop_lst.isDisplayed() && drop_lst.isEnabled())
		{
			new Select(drop_lst).selectByValue(value);
		}
	}


}
