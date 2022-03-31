package sauceDemo_cc;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Inventory;

public class Sd_stepDef
{
	//created an object for Inventory class to call all the following methods
	Inventory inventory = new Inventory();

	@Given("^the user is on login page$")
	public void the_user_is_on_login_page() throws Throwable {
		inventory.setUp();
		inventory.initialize("Chrome");
		inventory.checkUrl();
		inventory.getTitle();
	}

	@When("^the details are entered$")
	public void the_details_are_entered() throws Throwable {
		inventory.login();
	}

	@When("^the option in the drop down is selected$")
	public void the_option_in_the_drop_down_is_selected() throws Throwable {
		inventory.sorting();
		inventory.file("Sorted_Items");
		inventory.selectItem();
	}

	@Then("^the items are sorted$")
	public void the_items_are_sorted() throws Throwable {
		inventory.shoppingCart();
	}

	@And("^three items are added to the cart$")
	public void three_items_are_added_to_the_cart() throws Throwable {
		inventory.file("Added_Items");
	}

	@When("^one item is removed from the cart$")
	public void one_item_is_removed_from_the_cart() throws Throwable {
		inventory.removeItem();
	}

	@Then("^the cart is updated$")
	public void the_cart_is_updated() throws Throwable {
		inventory.file("Updated_Cart");
	}

	@And("^the browser quits$")
	public void the_browser_quits() throws Throwable {
		inventory.teardown();
	}

}
