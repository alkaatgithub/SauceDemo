package objects;

import org.openqa.selenium.By;


/*Page Object Model for all required Elements is declared*/
public class Elements {

	//Login Elements
	public static By username = By.id("user-name");
	public static By password = By.id("password");
	public static By login = By.id("login-button");
	public static By err_msg = By.cssSelector("h3[data-test='error']");

	//Inventory Elements
	public static By prod_nms=By.cssSelector("div.inventory_item_name");
	public static By prices=By.cssSelector("div.inventory_item_price");
	public static By options=By.cssSelector("select.product_sort_container");
	public static By addTocart=By.cssSelector("button[class='btn btn_primary btn_small btn_inventory']");
	public static By cart= By.cssSelector("a.shopping_cart_link");
	public static By shoppingCart=By.cssSelector("a.shopping_cart_link");
	public static By removeItem=By.cssSelector("button[class='btn btn_secondary btn_small cart_button']:nth-of-type(1)");

}
