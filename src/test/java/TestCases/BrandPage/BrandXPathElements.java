package TestCases.BrandPage;

import crisscrosscrass.countries;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BrandXPathElements {
    final static Logger logger = Logger.getLogger(BrandXPathElements.class);
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
        //option.addArguments("--disable-notifications");
        //option.addArguments("--start-maximized");
        //option.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", "temp//chromedriver.exe");
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void checkBrandPageQuicklinks(){
        locator = "brandpage.quicklinks";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationBrandOverviewPage()));
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }

    @Test
    public void checkBrandPageBox(){
        locator = "brandpage.brandbox";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationBrandOverviewPage()));
        Point hoverItem = driver.findElementByXPath(Homepage.getProperty("brandpage.brandbox")).getLocation();
        ((JavascriptExecutor)driver).executeScript("return window.title;");
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }

    @Test
    public void checkBrandPageBoxWrapperBottom(){
        locator = "brandpage.boxwrapper.bottom";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationBrandOverviewPage()));
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }

    @Test
    public void checkBrandPageBoxWrapperTop(){
        locator = "brandpage.boxwrapper.top";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationBrandOverviewPage()));
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }

    @Test
    public void checkBrandPageSearchBar(){
        locator = "brandpage.searchbar";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationBrandOverviewPage()));
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }

    @Test
    public void checkBrandPageSearchBarOption(){
        locator = "brandpage.searchbar.option";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        //does not work if window is not maximized
        driver.manage().window().maximize();
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationBrandOverviewPage()));
        driver.findElement(By.xpath(Homepage.getProperty("brandpage.searchbar"))).sendKeys("nike");

        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }

    @Test
    public void checkBrandPageSeriesBlock(){
        locator = "brandpage.series.block";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        //does not work if window is not maximized
        driver.manage().window().maximize();
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationBrandOverviewPage()));
        driver.findElement(By.xpath(Homepage.getProperty("brandpage.searchbar"))).sendKeys("nike");
        driver.findElement(By.xpath(Homepage.getProperty("brandpage.searchbar.option"))).click();
        try{
            element = driver.findElement (By.xpath(Homepage.getProperty(locator)));
        }catch (Exception xpathNotFound){
            logger.error("Couldn't find "+locator+" \n" +
                    Homepage.get(locator)+" | might be outdated");
        }
        Assert.assertNotNull(element);
    }

    @Test
    public void checkBrandPageSeriesLink(){
        locator = "brandpage.series.link";
        logger.info("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        //does not work if window is not maximized
        driver.manage().window().maximize();
        driver.get(String.valueOf(countries.valueOf(countrieSelection).getLocationBrandOverviewPage()));
        driver.findElement(By.xpath(Homepage.getProperty("brandpage.searchbar"))).sendKeys("nike");
        driver.findElement(By.xpath(Homepage.getProperty("brandpage.searchbar.option"))).click();
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
