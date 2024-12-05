package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.WebActions;
import com.microsoft.playwright.*;

public class LoginPage {
    private Page page;
    private final Locator USERNAME_EDITBOX;
    private final Locator PASSWORD_EDITBOX;
    private final Locator LOGIN_BUTTON;
    private final Locator BOOKS_SEARCH_BOX;
    private final Locator NEWUSER_BUTTON;

    public LoginPage(Page page) {
        this.page = page;
        this.USERNAME_EDITBOX = page.locator("#userName");
        this.PASSWORD_EDITBOX = page.locator("#password");
        this.LOGIN_BUTTON = page.locator("#login");
        this.BOOKS_SEARCH_BOX = page.getByPlaceholder("Type to search");
        this.NEWUSER_BUTTON = page.locator("#newUser");
    }

    public void navigateToUrl(String url) {
        this.page.navigate(WebActions.getProperty(url));
        try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void enterUsername(String username) {
        USERNAME_EDITBOX.fill(WebActions.getProperty(username));
    }

    public void enterPassword(String password) {
        PASSWORD_EDITBOX.fill(WebActions.decrypt(password));
    }

    public void clickLogin() {
        LOGIN_BUTTON.click();
    }
    public void clickNewUser() {
    	NEWUSER_BUTTON.click();
    }

    public void clickOnIcon(String iconName) {
        this.page.getByText(iconName, new Page.GetByTextOptions().setExact(true)).click();  // Clicks on the Exact text
    }

    public boolean verifyProfilePage() {
        return WebActions.waitUntilElementDisplayed(this.BOOKS_SEARCH_BOX, 60);
    }
}
