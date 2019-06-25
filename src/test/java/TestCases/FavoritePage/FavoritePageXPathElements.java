package TestCases.FavoritePage;

import crisscrosscrass.countries;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FavoritePageXPathElements {
    final static Logger logger = Logger.getLogger(FavoritePageXPathElements.class);
    private static ChromeDriver driver;
    private static Properties Homepage;
    private static String countrieSelection = "IT";
    private static String locator;
    WebElement element;

    @BeforeClass
    public static void openBrowser(){
        String resourceName = "configs/page.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Homepage = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            Homepage.load(resourceStream);
        }catch (Exception nope){
            nope.getStackTrace();
        }
        ChromeOptions option = new ChromeOptions();
        //option.addArguments("--disable-infobars");
        option.addArguments("--disable-notifications");
        //option.addArguments("--start-maximized");
        //option.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", "temp//chromedriver.exe");
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

/**my account test*/
    @Test
    public void check1ToMainAccountButton(){
        locator = "page.main.myaccount";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }
    @Test
    public void check2ToLogin(){
        locator = "page.myaccount.button.toLogin";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
        driver.findElementByXPath(Homepage.getProperty("page.main.myaccount")).click();
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }
    @Test
    public void check3ToRegister(){
        locator = "page.myaccount.button.toRegister";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
        driver.findElementByXPath(Homepage.getProperty("page.main.myaccount")).click();
        driver.findElementByXPath(Homepage.getProperty("page.myaccount.button.toLogin")).click();
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }
    @Test
    public void checkEmailInput(){
        locator = "page.myaccount.emailInput";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
        driver.findElementByXPath(Homepage.getProperty("page.main.myaccount")).click();
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }
    @Test
    public void checkPasswordInput(){
        locator = "page.myaccount.passwordInput";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
        driver.findElementByXPath(Homepage.getProperty("page.main.myaccount")).click();
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }
    @Test
    public void check5LogInButton(){
        locator = "page.myaccount.login.button";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
        driver.findElementByXPath(Homepage.getProperty("page.main.myaccount")).click();
        driver.findElementByXPath(Homepage.getProperty("page.myaccount.button.toLogin")).click();
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }
    @Test
    public void check4EnterCredentials(){
        locator = "page.myaccount.emailInput";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
        driver.findElementByXPath(Homepage.getProperty("page.main.myaccount")).click();
        driver.findElementByXPath(Homepage.getProperty("page.myaccount.button.toLogin")).click();
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
            element.sendKeys("tester@visual-meta.com");
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertEquals("tester@visual-meta.com", element.getAttribute("value"));
    }
 /**favorite page */
 @Test
 public void check6ItemsFavoriteFinal(){
     locator = "page.items.favorite.final";
     logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
     driver.manage().window().maximize();
     driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
     driver.findElementByXPath(Homepage.getProperty("page.main.links")).click();
     try{
         element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
     }catch (Exception xpathNotFound){
         logger.error("Couldn't find "+locator+" \n" +
                 Homepage.get(locator)+" | might be outdated");
     }
     Assert.assertNotNull(element);
 }


 @Test
 public void checkSortingDropdownButton(){
     locator = "page.myaccount.sorting.dropdown.button";
     logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
     driver.manage().window().maximize();
     driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
     driver.findElementByXPath(Homepage.getProperty("page.main.links")).click();
//add item
     Point hoverItem = driver.findElementByXPath(Homepage.getProperty("page.items.favorite.final")).getLocation();
     ((JavascriptExecutor)driver).executeScript("return window.title;");
     ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
     driver.findElementByXPath(Homepage.getProperty("page.items.favorite.final")).click();
//log in (xPaths are different when logged in and out )
     WebDriverWait wait = new WebDriverWait(driver, 5);

     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Homepage.getProperty("page.main.myaccount"))));
     driver.findElementByXPath(Homepage.getProperty("page.main.myaccount")).click();
     wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Homepage.getProperty("page.myaccount.button.toLogin"))));
     driver.findElementByXPath(Homepage.getProperty("page.myaccount.button.toLogin")).click();
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("page.myaccount.button.toRegister"))));
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("page.myaccount.emailInput"))));
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("page.myaccount.passwordInput"))));

     WebElement element = driver.findElementByXPath(Homepage.getProperty("page.myaccount.emailInput"));
     element.sendKeys("tamiris.diversi@visual-meta.com");
     element = driver.findElementByXPath(Homepage.getProperty("page.myaccount.passwordInput"));
     element.sendKeys("O4-");
     driver.findElementByXPath(Homepage.getProperty("page.myaccount.login.button")).click();

     try{
         element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
     }catch (Exception xpathNotFound){
         logger.error("Couldn't find "+locator+" \n" +
                 Homepage.get(locator)+" | might be outdated");
     }
     Assert.assertNotNull(element);
 }

    @Test
    public void checkSortingDropdownOptions(){
        locator = "page.myaccount.sorting.dropdown.options";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.manage().window().maximize();
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
        driver.findElementByXPath(Homepage.getProperty("page.main.links")).click();
//add item
        Point hoverItem = driver.findElementByXPath(Homepage.getProperty("page.items.favorite.final")).getLocation();
        ((JavascriptExecutor)driver).executeScript("return window.title;");
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
        driver.findElementByXPath(Homepage.getProperty("page.items.favorite.final")).click();
//log in (xPaths are different when logged in and out )
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Homepage.getProperty("page.main.myaccount"))));
        driver.findElementByXPath(Homepage.getProperty("page.main.myaccount")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Homepage.getProperty("page.myaccount.button.toLogin"))));
        driver.findElementByXPath(Homepage.getProperty("page.myaccount.button.toLogin")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("page.myaccount.button.toRegister"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("page.myaccount.emailInput"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("page.myaccount.passwordInput"))));

        WebElement element = driver.findElementByXPath(Homepage.getProperty("page.myaccount.emailInput"));
        element.sendKeys("tamiris.diversi@visual-meta.com");
        element = driver.findElementByXPath(Homepage.getProperty("page.myaccount.passwordInput"));
        element.sendKeys("O4-");
        driver.findElementByXPath(Homepage.getProperty("page.myaccount.login.button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("page.myaccount.sorting.dropdown.button"))));
        driver.findElementByXPath(Homepage.getProperty("page.myaccount.sorting.dropdown.button")).click();
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }


    @Test
    public void checkItemsFavoriteDelete(){
        locator = "page.items.favorite.delete";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.manage().window().maximize();
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationMainPage()));
        driver.findElementByXPath(Homepage.getProperty("page.main.links")).click();
//add item
        Point hoverItem = driver.findElementByXPath(Homepage.getProperty("page.items.favorite.final")).getLocation();
        ((JavascriptExecutor)driver).executeScript("return window.title;");
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
        driver.findElementByXPath(Homepage.getProperty("page.items.favorite.final")).click();
//log in (xPaths are different when logged in and out )
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Homepage.getProperty("page.main.myaccount"))));
        driver.findElementByXPath(Homepage.getProperty("page.main.myaccount")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Homepage.getProperty("page.myaccount.button.toLogin"))));
        driver.findElementByXPath(Homepage.getProperty("page.myaccount.button.toLogin")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("page.myaccount.button.toRegister"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("page.myaccount.emailInput"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("page.myaccount.passwordInput"))));

        WebElement element = driver.findElementByXPath(Homepage.getProperty("page.myaccount.emailInput"));
        element.sendKeys("tamiris.diversi@visual-meta.com");
        element = driver.findElementByXPath(Homepage.getProperty("page.myaccount.passwordInput"));
        element.sendKeys("Oasis1234-");
        driver.findElementByXPath(Homepage.getProperty("page.myaccount.login.button")).click();

        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }

    @AfterClass
    public static void closeBrowser(){
        try {
            driver.close();
        }catch (Exception driverClosing){
            driverClosing.printStackTrace();
        }
        try {
            driver.quit();
        }catch (Exception driverQuit){
            driverQuit.printStackTrace();
        }
    }

}
