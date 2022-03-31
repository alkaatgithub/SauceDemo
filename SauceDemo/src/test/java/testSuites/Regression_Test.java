package testSuites;

import org.testng.annotations.Test;

import pages.Inventory;

public class Regression_Test 
{
	/*to call the required methods for regression testing*/
	@Test
	public void regressionTest() throws Exception 
	{
		//created an object for Inventory class to call all the following methods
		Inventory invent = new Inventory();		

		invent.checkUrl();
		invent.login();
		invent.sorting();
		invent.file("Sorted_Items");
		invent.selectItem();
		invent.shoppingCart();
		invent.file("Added_Items");
		invent.removeItem();
		invent.file("Updated_Cart");

	}
}
