package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.wordpress.PostsPageObject;
import pageUIs.wordpress.BasePageUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);

    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();

    }

    protected String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();

    }

    protected String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();

    }

    protected void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    protected void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();

    }

    protected Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    protected void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(5);

    }

    protected Alert waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, 30);
        return explicitWait.until(ExpectedConditions.alertIsPresent());

    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getTextAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void senkeytoAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    protected void switchToExpectWindow(WebDriver driver, String expectedPageTitle) {
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id : allWindowsIDs) {
            driver.switchTo().window(id);

            String actualPageTitle = driver.getTitle();
            if (actualPageTitle.equals(expectedPageTitle)) {
                break;

            }

        }

    }

    private By getByLocator(String locatorType) {
        By by = null;
        System.out.println("Locator type= " + locatorType);
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=")
                || locatorType.startsWith("CLASS=")) {
            by = By.className(locatorType.substring(6));

        } else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=")
                || locatorType.startsWith("XPATH=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator Type is not supported");
        }
        return by;

    }

    protected String getDynamicXpath(String locatorType, String... dynamicValues) {
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
            locatorType = String.format(locatorType, (Object[]) dynamicValues);

        }
        return locatorType;

    }

    protected WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    protected WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValue) {
        return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValue)));
    }

    public List<WebElement> getListElement(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));

    }

    public List<WebElement> getListElement(WebDriver driver, String locatorType, String... dynamicValue) {
        return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValue)));

    }

    public void clickToElement(WebDriver driver, String locatorType) {
        getWebElement(driver, locatorType).click();
    }

    public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
    }

    public void sendkeysToElement(WebDriver driver, String locatorType, String inputValue) {
        getWebElement(driver, locatorType).clear();
        getWebElement(driver, locatorType).sendKeys(inputValue);

    }

    public void sendkeysToElement(WebDriver driver, String locatorType, String inputValue, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        element.clear();
        element.sendKeys(inputValue);

    }

    public void sendkeysToElement(WebDriver driver, String locatorType, String[] text) {
        getWebElement(driver, getDynamicXpath(locatorType)).clear();
        getWebElement(driver, getDynamicXpath(locatorType)).sendKeys(text);

    }

    public void selectItemByValueInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
        Select select = new Select(getWebElement(driver, locatorType));
        select.selectByValue(textItem);
    }

    public void selectItemByTextInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
        Select select = new Select(getWebElement(driver, locatorType));
        select.selectByVisibleText(textItem);
    }

    public void selectItemByValueInDefaultDropdown(WebDriver driver, String locatorType, String textItem,
                                                   String... dynamicValues) {
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
        select.selectByValue(textItem);

    }

    protected void selectItemByTextInDefaultDropdown(WebDriver driver, String locatorType, String textItem,
                                                     String... dynamicValues) {
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
        select.selectByVisibleText(textItem);

    }

    protected String getSelectedItemInDropdown(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.isMultiple();

    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
                                              String expectedItem) {
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);

        WebDriverWait explicitWait = new WebDriverWait(driver, 30);
        List<WebElement> allItems = explicitWait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);

                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    protected String getAttributeValue(WebDriver driver, String locatorType, String name) {
        return getWebElement(driver, locatorType).getAttribute(name);

    }

    protected String getTextElement(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();

    }

    protected String getTextElement(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).getText();

    }

    protected String getCssValue(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).getCssValue(locatorType);

    }

    protected int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
        return getListElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
    }

    protected void checktoDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (element.isSelected() != true) {
            element.click();
        }
    }

    protected void checktoDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        if (element.isSelected() != true) {
            element.click();
        }
    }

    protected void unchecktoDefaultCheckbox(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (element.isSelected() == true) {
            element.click();
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
        try {
            return getWebElement(driver, locatorType).isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }

    }

    protected boolean isDynamicElementDisplayed(WebDriver driver, String locatorType, String... dynamicValue) {
        try {
            return getWebElement(driver, locatorType, dynamicValue).isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }

    }

    protected void overrideGlobalTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locatorType) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListElement(driver, locatorType);
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

        if (elements.size() == 0) {
            System.out.println("Element is not in DOM");
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element is in DOM but undisplayed");
            return true;

        } else {
            System.out.println("Element is in DOM and visible");
            return false;
        }

    }

    protected boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValue) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListElement(driver, locatorType, dynamicValue);
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

        if (elements.size() == 0) {
            System.out.println("Element is not in DOM");
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element is in DOM but undisplayed");
            return true;

        } else {
            System.out.println("Element is in DOM and visible");
            return false;
        }

    }

    protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();

    }

    protected boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();

    }

    protected boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();

    }

    protected void switchToIframeFrame(WebDriver driver, String xpathLocator) {
        driver.switchTo().frame(getWebElement(driver, xpathLocator));

    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();

    }

    protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, xpathLocator)).perform();

    }

    protected void hoverMouseToElement(WebDriver driver, String xpathLocator, String... dynamicValue) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, xpathLocator, dynamicValue)).perform();

    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollToTopPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(document.body.scrollHeight,0)");
    }

    protected void highlightElement(WebDriver driver, String locatorType, String dynamicValues) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
                "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
                "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
    }


    public void scrollToElement(WebDriver driver, String locatorType) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                getWebElement(driver, locatorType));
    }

    public void scrollToElement(WebDriver driver, String locatorType, String... dynamicValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
                getWebElement(driver, locator));
    }

    protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, 30);
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected String getElementValidationMessage(WebDriver driver, String locatorType, String... DynamicValues) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
                getWebElement(driver, getDynamicXpath(locatorType, DynamicValues)));
    }

    protected boolean isImageLoaded(WebDriver driver, String locatorType) {
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getWebElement(driver, locatorType));
        if (status) {
            return true;

        } else {
            return false;
        }
    }

    protected boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getWebElement(driver, getDynamicXpath(locatorType)));
        return status;
    }

    protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(
                ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }


    protected void uploadMultipleFiles(WebDriver driver, String locator, String... fileNames) {
        String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");
        System.out.println("file Path = " + filePath);

        String fullFileName = "";

        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }

        fullFileName = fullFileName.trim();
        getWebElement(driver, locator).sendKeys(fullFileName);
    }

    protected boolean areFileUploadedSuccess(WebDriver driver, String locator, String... fileNames) {
        boolean status = false;
        for (String fileName : fileNames) {
            if (isElementDisplayed(driver, locator, fileName)) {
                status = true;
            } else {
                return status;
            }
        }
        return status;
    }

    protected String getDirectorySlash(String folderName) {
        String separator = System.getProperty("file.separator");
        return separator + folderName + separator;
    }

    protected boolean isDataStringSortedAscendingString(WebDriver driver, String locator) {
        // Declare array list
        ArrayList<String> arrayList = new ArrayList<>();

        // Find all elements matching the condition (Name/Price/...)
        List<WebElement> elementList = getListElement(driver, locator);

        // Get the text of each element and add it to the Array List
        for (WebElement element : elementList) {
            arrayList.add(element.getText());
        }
        System.out.println("--------Data on UI:");
        for (String name : arrayList) {
            System.out.println(name);
        }

        // Copy to a new array list to SORT in Code
        ArrayList<String> sortedList = new ArrayList<>();
        for (String child : arrayList) {
            sortedList.add(child);
        }

        // SORT DESC:
        Collections.sort(sortedList);
        System.out.println("-------- asc sorted data in code:-----------");
        for (String name : sortedList) {
            System.out.println(name);
        }

        // Verify that the 2 arrays are equal - if the UI sort data is incorrect, the
        // result is incorrect
        return sortedList.equals(arrayList);
    }

    protected boolean isDataStringSortedDescendingString(WebDriver driver, String locator) {
        // Declare array list
        ArrayList<String> arrayList = new ArrayList<>();

        // Find all elements matching the condition (Name/Price/...)
        List<WebElement> elementList = getListElement(driver, locator);

        // Get the text of each element and add it to the Array List
        for (WebElement element : elementList) {
            arrayList.add(element.getText());
        }
        System.out.println("--------Data on UI:");
        for (String name : arrayList) {
            System.out.println(name);
        }

        // Copy to a new array list to SORT in Code
        ArrayList<String> sortedList = new ArrayList<>();
        for (String child : arrayList) {
            sortedList.add(child);
        }

        // SORT DESC:
        Collections.sort(sortedList);
        System.out.println("--------asc sorted data in code:-----------");
        for (String name : sortedList) {
            System.out.println(name);
        }
        // Reverse data to sort DESC
        Collections.reverse(sortedList);
        System.out.println("--------desc sorted data in code:-----------");
        for (String name : sortedList) {
            System.out.println(name);
        }
        // Verify that the 2 arrays are equal - if the UI sort data is incorrect, the
        // result is incorrect
        return sortedList.equals(arrayList);
    }

    protected boolean isDataFloatSortedDescending(WebDriver driver, String priceLocator) {
        // Declare array list
        ArrayList<Float> arrayList = new ArrayList<>();

        // Find all elements matching the condition (Name/Price/...)
        List<WebElement> elementList = getListElement(driver, priceLocator);

        // Get the text of each element and add it to the Array List
        for (WebElement element : elementList) {
            if (element.getText().contains(",")) {
                arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
            } else if (element.getText().isEmpty()) {
                arrayList.add((float) 0);

            } else if (element.getText().contains("per 1 day(s)")) {
                arrayList.add(Float.parseFloat(
                        element.getText().replace("$", "").replace(",", "").replace("per 1 day(s)", "").trim()));
            } else
                arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
        }
        System.out.println("--------Data on UI:");
        for (Float price : arrayList) {
            System.out.println(price);
        }

        // Copy to a new array list to SORT in Code
        ArrayList<Float> sortedList = new ArrayList<>();
        for (Float child : arrayList) {
            sortedList.add(child);
        }

        // SORT ASC:
        Collections.sort(sortedList);
        System.out.println("--------asc sorted data in code:-----------");
        for (Float name : sortedList) {
            System.out.println(name);
        }
        // Reverse data để sort DESC
        Collections.reverse(sortedList);
        System.out.println("--------desc sorted data in code:-----------");
        for (Float name : sortedList) {
            System.out.println(name);
        }
        // Verify that the 2 arrays are equal - if the UI sort data is incorrect, the
        // result is incorrect
        return sortedList.equals(arrayList);
    }


    protected boolean isDataFloatSortedAscending(WebDriver driver, String locator) {
        // Declare array list
        ArrayList<Float> arrayList = new ArrayList<>();

        // Find all elements matching the condition (Name/Price/...)
        List<WebElement> elementList = getListElement(driver, locator);

        // Get the text of each element and add it to the Array List
        for (WebElement element : elementList) {
            if (element.getText().contains(",")) {
                arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
            } else if (element.getText().isEmpty()) {
                arrayList.add((float) 0);
            } else if (element.getText().contains("per 1 day(s)")) {
                arrayList.add(Float.parseFloat(
                        element.getText().replace("$", "").replace(",", "").replace("per 1 day(s)", "").trim()));
            } else
                arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
        }
        System.out.println("--------Data on UI:");
        for (Float price : arrayList) {
            System.out.println(price);
        }

        // Copy to a new array list to SORT in Code
        ArrayList<Float> sortedList = new ArrayList<>();
        for (Float child : arrayList) {
            sortedList.add(child);
        }

        // Reverse data to sort DESC
        Collections.sort(sortedList);
        System.out.println("--------asc sorted data in code:-----------");
        for (Float price : sortedList) {
            System.out.println(price);
        }
        // Verify that the 2 arrays are equal - if the UI sort data is incorrect, the
        // result is incorrect
        return sortedList.equals(arrayList);
    }


    public String getRandomPhoneNumber() {
        return "0" + ThreadLocalRandom.current().nextInt(10000000, 99999999);
    }

    public void selectRandomRadiobutton(WebDriver driver, String locator) {
        Random rnd = new Random();
        List<WebElement> radios = getListElement(driver, locator);
        if (!radios.isEmpty()) {
            radios.get(rnd.nextInt(radios.size())).click();
        }
    }

    public BasePage clickToMenuByText(WebDriver driver, String text) {
        waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_BY_TEXT, text);
        clickToElement(driver, BasePageUI.DYNAMIC_PAGE_BY_TEXT, text);
        switch (text) {
            case "Posts":
                return WordpressPageGeneraterManager.getPostPage(driver);
            case "Categories":
                return WordpressPageGeneraterManager.getAdminCategoriesListPage(driver);
            default:
                return null;
        }


    }

    public void typeEnterKey(WebDriver driver, String locator) {
        getWebElement(driver, locator).sendKeys(Keys.ENTER);

    }

    public String getRandomImageName() {
        List<String> imageNames = List.of("image_01.jpg", "image_02.jpg", "image_03.jpg", "image_04.jpg", "image_05.jpg",
                "image_06.jpg", "image_07.jpg", "image_08.jpg", "image_09.jpg", "image_10.jpg", "image_11.jpg", "image_12.jpg", "image_13.jpg", "image_14.jpg", "image_15.jpg",
                "image_16.jpg", "image_17.jpg", "image_18.jpg", "image_19.jpg", "image_20.jpg", "image_21.jpg", "image_22.jpg", "image_23.jpg", "image_24.jpg", "image_25");
        Random random = new Random();
        int randomIndex = random.nextInt(imageNames.size());
        return imageNames.get(randomIndex);
    }


    public void inputToTextboxByID(WebDriver driver, String id, String text) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
        sendkeysToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, text, id);
    }

    public void clickToInputTypeButtonByValue(WebDriver driver, String value) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_INPUT_TYPE_BUTTON_BY_VALUE, value);
        clickToElement(driver, BasePageUI.DYNAMIC_INPUT_TYPE_BUTTON_BY_VALUE, value);
    }

    public void clickToButtonWithSubmitTypeByText(WebDriver driver, String text) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_BUTTON_WITH_SUBMIT_TYPE_BY_TEXT, text);
        clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_WITH_SUBMIT_TYPE_BY_TEXT, text);
    }

    public void clickToButtonWithSubmitTypeByValue(WebDriver driver, String value) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_BUTTON_WITH_SUBMIT_TYPE_BY_VALUE, value);
        clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_WITH_SUBMIT_TYPE_BY_VALUE, value);
    }

    public String convertDateFormat(String inputDate) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM dd, yyyy");
        Date date = inputFormat.parse(inputDate);

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
        return outputFormat.format(date);
    }

    public String getNoItemsMessage(WebDriver driver) {
        waitForElementVisible(driver, BasePageUI.NO_ITEMS_MESSAGE);
        return getTextElement(driver, BasePageUI.NO_ITEMS_MESSAGE);
    }

    public void selectRandomSelectionInDropdown(WebDriver driver, String locator) {
        Random rnd = new Random();
        List<WebElement> selection = getListElement(driver, locator);
        if (!selection.isEmpty()) {
            selection.get(rnd.nextInt(selection.size())).click();
        }
    }

    public void clickToButtonWithInputTagSubmitTypeByValue(WebDriver driver, String value) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_INPUT_WITH_SUBMIT_TYPE_BY_VALUE, value);
        clickToElement(driver, BasePageUI.DYNAMIC_INPUT_WITH_SUBMIT_TYPE_BY_VALUE, value);
    }

    public void hoverToElement(WebDriver driver, String locator) {
        WebElement elementToHover = getWebElement(driver, getDynamicXpath(locator));
        // Create an instance of the Actions class
        Actions actions = new Actions(driver);

        // Perform the hover action on the element
        actions.moveToElement(elementToHover).perform();

    }

}
