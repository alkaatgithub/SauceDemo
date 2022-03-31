package testSuites;

import org.testng.annotations.Test;

import pages.Inventory;

public class Smoke_Test
{
	/*to call all the required methods for smoke testing*/
	@Test
	public void smokeTest() throws Exception 
	{
		//created an object for Inventory class to call all the following methods
		Inventory invent = new Inventory();

		invent.checkUrl();
		invent.getTitle();
		invent.login();
	}
}
