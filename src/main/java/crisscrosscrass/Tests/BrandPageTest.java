package crisscrosscrass.Tests;

import com.jfoenix.controls.JFXCheckBox;
import crisscrosscrass.Tasks.ChangeCheckBox;
import crisscrosscrass.Tasks.Report;
import crisscrosscrass.Tasks.ScreenshotViaWebDriver;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrandPageTest {
    Report failedTestCases = new Report();

    public void checkingBrandsWithoutLogoAllBrands(ChromeDriver webDriver, Report report, JavascriptExecutor js, JFXCheckBox brandsWithoutLogo, TextField inputBrandPageOverview, Text statusInfo, TextField inputSearch, Properties Homepage){
        failedTestCases.writeToNamedFile("CHECKING BRANDS PAGE", "FailAndReview");
        final String infoMessage = brandsWithoutLogo.getText();
        ChangeCheckBox.adjustStyle(false,"progress",brandsWithoutLogo);
        Platform.runLater(() -> {
            statusInfo.setText(""+infoMessage+"...");
        });
        try {
            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(0));
            try {
                webDriver.navigate().to(inputBrandPageOverview.getText().trim());
                WebDriverWait wait = new WebDriverWait(webDriver, 10);
                try{
                    boolean isAvailable = webDriver.findElementByXPath(Homepage.getProperty("brandpage.quicklinks")) != null;
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("brandpage.quicklinks"))));
                    List<WebElement> quickMenuLetters = webDriver.findElementsByXPath(Homepage.getProperty("brandpage.quicklinks"));
                    ArrayList allCollectedLinks = new ArrayList();
                    for (WebElement FillIns : quickMenuLetters){
                        allCollectedLinks.add(FillIns.getAttribute("href").trim().toLowerCase());
                    }
                    for (int i = 0 ; i < allCollectedLinks.size() ; i++){
                        ((JavascriptExecutor)webDriver).executeScript("window.open()");
                        tabs = new ArrayList<>(webDriver.getWindowHandles());
                        webDriver.switchTo().window(tabs.get(1)); //switches to new tab
                        webDriver.get(allCollectedLinks.get(i).toString());
                        report.writeToFile("Missing Logos on page " + webDriver.getCurrentUrl() + " :");
                        failedTestCases.writeToNamedFile("Missing Logos on page " + webDriver.getCurrentUrl() + " :", "FailAndReview");


                        if(webDriver.findElements(By.xpath(Homepage.getProperty("brandpage.boxwrapper.top"))).size() > 0){
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("brandpage.boxwrapper.top"))));
                            List<WebElement> boxWrapperTop = webDriver.findElementsByXPath(Homepage.getProperty("brandpage.boxwrapper.top"));
                            for (WebElement quickMenuItem : boxWrapperTop){
                                report.writeToFile(quickMenuItem.getText());
                                failedTestCases.writeToNamedFile(quickMenuItem.getText(), "FailAndReview");
                            }
                        }
                        if(webDriver.findElements(By.xpath(Homepage.getProperty("brandpage.boxwrapper.bottom"))).size() > 0){
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("brandpage.boxwrapper.bottom"))));
                            List<WebElement> boxWrapperBottom = webDriver.findElementsByXPath(Homepage.getProperty("brandpage.boxwrapper.bottom"));
                            for (WebElement quickMenuItem : boxWrapperBottom){
                                report.writeToFile(quickMenuItem.getText());
                                failedTestCases.writeToNamedFile(quickMenuItem.getText(), "FailAndReview");
                            }
                        }
                        if(webDriver.findElements(By.xpath(Homepage.getProperty("brandpage.brandbox"))).size() > 0){
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("brandpage.brandbox"))));
                            List<WebElement> brandboxes = webDriver.findElementsByXPath(Homepage.getProperty("brandpage.brandbox"));
                            for (WebElement quickMenuItem : brandboxes){
                                report.writeToFile(quickMenuItem.getText());
                                failedTestCases.writeToNamedFile(quickMenuItem.getText(), "FailAndReview");
                            }
                        }
                        webDriver.switchTo().window(tabs.get(1)).close();
                        webDriver.switchTo().window(tabs.get(0));
                    }
                    ChangeCheckBox.adjustStyle(true,"complete",brandsWithoutLogo);
                    report.writeToFile(infoMessage, "Successful! Redirected to a functioning page!");
                }catch (Exception gridPageIssue){
                    ChangeCheckBox.adjustStyle(true,"nope",brandsWithoutLogo);
                    boolean isSuccessful = ScreenshotViaWebDriver.printScreen(webDriver, "ErrorCheckingBrandPageOverview.png");
                    if (isSuccessful){
                        report.writeToFile("BrandPage Error Screenshot: ", "Screenshot successful!");
                        failedTestCases.writeToNamedFile("BrandPage Error Screenshot", "Please check functionality of Brand Page. For reference, see ErrorCheckingBrandPageOverview", "FailAndReview");
                    }else {
                        report.writeToFile("BrandPage Error Screenshot: ", "Screenshot not successful!");
                        failedTestCases.writeToNamedFile("BrandPage Error Screenshot", "Screenshot not successful!", "FailAndReview");
                    }
                    webDriver.navigate().to(inputSearch.getText().trim());
                    report.writeToFile(infoMessage, "Couldn't find any QuickMenu Elements");
                    failedTestCases.writeToNamedFile(infoMessage, "Please check Brand Page: could not find menu element", "FailAndReview");
                    gridPageIssue.printStackTrace();
                }
            }catch (Exception noRequestedSiteFound){
                ChangeCheckBox.adjustStyle(true,"nope",brandsWithoutLogo);
                webDriver.navigate().to(inputSearch.getText().trim());
                report.writeToFile(infoMessage, "Couldn't navigate to requested Site!");
                failedTestCases.writeToNamedFile(infoMessage, "Please check Brand Page: could not navigate to requested site", "FailAndReview");
                noRequestedSiteFound.printStackTrace();
            }
        }catch (Exception noBrowserWorking){
            ChangeCheckBox.adjustStyle(true,"nope",brandsWithoutLogo);
            report.writeToFile(infoMessage, "unable to check! Browser not responding");
            failedTestCases.writeToNamedFile(infoMessage, "Please check Brand Page: browser not responding", "FailAndReview");
            noBrowserWorking.printStackTrace();
        }
        report.writeToFile("=================================", "");
        failedTestCases.writeToNamedFile("=================================TC 20","FailAndReview");

    }

    /** First Page Only */
    public void checkingBrandsWithoutLogo(ChromeDriver webDriver, Report report, JavascriptExecutor js, JFXCheckBox brandsWithoutLogo, TextField inputBrandPageOverview, Text statusInfo, TextField inputSearch, Properties Homepage){
        failedTestCases.writeToNamedFile("CHECKING BRANDS PAGE", "FailAndReview");
        final String infoMessage = brandsWithoutLogo.getText();
        ChangeCheckBox.adjustStyle(false,"progress",brandsWithoutLogo);
        Platform.runLater(() -> {
            statusInfo.setText(""+infoMessage+"...");
        });
        try {
            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(0));
            try {
                webDriver.navigate().to(inputBrandPageOverview.getText().trim());
                WebDriverWait wait = new WebDriverWait(webDriver, 10);
                try{
                        if(webDriver.findElements(By.xpath(Homepage.getProperty("brandpage.boxwrapper.top"))).size() > 0){
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("brandpage.boxwrapper.top"))));
                            List<WebElement> boxWrapperTop = webDriver.findElementsByXPath(Homepage.getProperty("brandpage.boxwrapper.top"));
                            failedTestCases.writeToNamedFile("==========Brands without Logo on Main Brand page:==========", "FailAndReview");
                            for (WebElement quickMenuItem : boxWrapperTop){
                                report.writeToFile(quickMenuItem.getText());
                                failedTestCases.writeToNamedFile(quickMenuItem.getText(), "FailAndReview");
                            }
                        }
                        if(webDriver.findElements(By.xpath(Homepage.getProperty("brandpage.boxwrapper.bottom"))).size() > 0){
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("brandpage.boxwrapper.bottom"))));
                            List<WebElement> boxWrapperBottom = webDriver.findElementsByXPath(Homepage.getProperty("brandpage.boxwrapper.bottom"));
                            for (WebElement quickMenuItem : boxWrapperBottom){
                                report.writeToFile(quickMenuItem.getText());
                                failedTestCases.writeToNamedFile(quickMenuItem.getText(), "FailAndReview");
                            }
                        }
                        if(webDriver.findElements(By.xpath(Homepage.getProperty("brandpage.brandbox"))).size() > 0){
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("brandpage.brandbox"))));
                            List<WebElement> brandboxes = webDriver.findElementsByXPath(Homepage.getProperty("brandpage.brandbox"));
                            for (WebElement quickMenuItem : brandboxes){
                                report.writeToFile(quickMenuItem.getText());
                                failedTestCases.writeToNamedFile(quickMenuItem.getText(), "FailAndReview");
                            }
                        }


                    ChangeCheckBox.adjustStyle(true,"complete",brandsWithoutLogo);
                    report.writeToFile(infoMessage, "Successful! Redirected to a functioning page!");
                }catch (Exception gridPageIssue){
                    ChangeCheckBox.adjustStyle(true,"nope",brandsWithoutLogo);
                    boolean isSuccessful = ScreenshotViaWebDriver.printScreen(webDriver, "ErrorCheckingBrandPageOverview.png");
                    if (isSuccessful){
                        report.writeToFile("BrandPage Error Screenshot: ", "Screenshot successful!");
                        failedTestCases.writeToNamedFile("BrandPage Error Screenshot", "Please check functionality of Brand Page. For reference, see ErrorCheckingBrandPageOverview", "FailAndReview");
                    }else {
                        report.writeToFile("BrandPage Error Screenshot: ", "Screenshot not successful!");
                        failedTestCases.writeToNamedFile("BrandPage Error Screenshot", "Screenshot not successful!", "FailAndReview");
                    }
                    webDriver.navigate().to(inputSearch.getText().trim());
                    report.writeToFile(infoMessage, "Couldn't find any QuickMenu Elements");
                    failedTestCases.writeToNamedFile(infoMessage, "Please check Brand Page: could not find menu element", "FailAndReview");
                    gridPageIssue.printStackTrace();
                }
            }catch (Exception noRequestedSiteFound){
                ChangeCheckBox.adjustStyle(true,"nope",brandsWithoutLogo);
                webDriver.navigate().to(inputSearch.getText().trim());
                report.writeToFile(infoMessage, "Couldn't navigate to requested Site!");
                failedTestCases.writeToNamedFile(infoMessage, "Please check Brand Page: could not navigate to requested site", "FailAndReview");
                noRequestedSiteFound.printStackTrace();
            }
        }catch (Exception noBrowserWorking){
            ChangeCheckBox.adjustStyle(true,"nope",brandsWithoutLogo);
            report.writeToFile(infoMessage, "unable to check! Browser not responding");
            failedTestCases.writeToNamedFile(infoMessage, "Please check Brand Page: browser not responding", "FailAndReview");
            noBrowserWorking.printStackTrace();
        }
        report.writeToFile("=================================", "");
        failedTestCases.writeToNamedFile("=================================TC 20","FailAndReview");

    }
    /**end of first page only */
    public void checkingBrandSearchAndSeries(ChromeDriver webDriver, Report report, JavascriptExecutor js, JFXCheckBox brandSearchAndSeries, TextField inputBrandPageOverview, TextField inputGridPageKeyword, Text statusInfo, TextField inputSearch, Properties Homepage){
        final String infoMessage = brandSearchAndSeries.getText();
        ChangeCheckBox.adjustStyle(false,"progress",brandSearchAndSeries);
        Platform.runLater(() -> {
            statusInfo.setText(""+infoMessage+"...");
        });
        try {
            ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(0));
            try {
                webDriver.navigate().to(inputBrandPageOverview.getText().trim());
                WebDriverWait wait = new WebDriverWait(webDriver, 10);
                try{
                   //Find element brand search
                    //TODO: add input box for brand
                    webDriver.findElement(By.xpath(Homepage.getProperty("brandpage.searchbar"))).sendKeys(inputGridPageKeyword.getText().trim());
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("brandpage.searchbar.option"))));
                    webDriver.findElement(By.xpath(Homepage.getProperty("brandpage.searchbar.option"))).click();

                    //click on nike and wait to be redirected to a new page
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("general.h1"))));

                    //if nike is on h1 or url
                    String brandH1 = webDriver.findElement(By.xpath(Homepage.getProperty("general.h1"))).getText().toLowerCase();
                    if (brandH1.contains(inputGridPageKeyword.getText().trim())){
                        report.writeToFile("BrandPage search Bar: ", "Redirected to brand page successfully");
                    }
                    else {
                        report.writeToFile("BrandPage search Bar: ", "Redirect to brand page was NOT successful");
                        failedTestCases.writeToNamedFile("Please check BrandPage search Bar: ", "Redirect to brand page was NOT successful", "FailAndReview");
                    }
                    //scroll down to series box
                    Point hoverItem = webDriver.findElement(By.xpath(Homepage.getProperty("brandpage.series.block"))).getLocation();
                    ((JavascriptExecutor)webDriver).executeScript("return window.title;");
                    ((JavascriptExecutor)webDriver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
                    //get name and clikc on it
                    String seriesName = webDriver.findElement(By.xpath(Homepage.getProperty("brandpage.series.link"))).getText().toLowerCase();
                    webDriver.findElement(By.xpath(Homepage.getProperty("brandpage.series.link"))).click();

                    //compare to h1 in series page
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Homepage.getProperty("general.h1"))));

                    String seriesH1 = webDriver.findElement(By.xpath(Homepage.getProperty("general.h1"))).getText().toLowerCase();
                    if (seriesH1.contains(seriesName)){
                        ChangeCheckBox.adjustStyle(true,"complete",brandSearchAndSeries);
                        report.writeToFile("Series Box clickout: ", "Redirected to series page successfully");

                    }
                    else {
                        report.writeToFile("Series Box clickout: ", "Redirect to series page was NOT successful");
                        failedTestCases.writeToNamedFile("Please check Series Page search Bar: ", "Redirect to Series page was NOT successful", "FailAndReview");
                        ChangeCheckBox.adjustStyle(true,"nope",brandSearchAndSeries);

                    }

                    report.writeToFile(infoMessage, "Successful! Redirected to a functioning page!");
                }catch (Exception gridPageIssue){
                    ChangeCheckBox.adjustStyle(true,"nope",brandSearchAndSeries);
                    boolean isSuccessful = ScreenshotViaWebDriver.printScreen(webDriver, "ErrorCheckingBrandPageOverview.png");
                    if (isSuccessful){
                        report.writeToFile("BrandPage Error Screenshot: ", "Screenshot successful!");
                        failedTestCases.writeToNamedFile("BrandPage Error Screenshot", "Please check Search functionality of Brand Page. For reference, see ErrorCheckingBrandPageOverview", "FailAndReview");
                    }else {
                        report.writeToFile("BrandPage Error Screenshot: ", "Screenshot not successful!");
                        failedTestCases.writeToNamedFile("BrandPage Error Screenshot", "Screenshot not successful!", "FailAndReview");
                    }
                    webDriver.navigate().to(inputSearch.getText().trim());
                    report.writeToFile(infoMessage, "Couldn't find any QuickMenu Elements");
                    failedTestCases.writeToNamedFile(infoMessage, "Please check Brand Page: could not find Search element", "FailAndReview");
                    failedTestCases.writeToNamedFile("=================================TC 21","FailAndReview");
                    gridPageIssue.printStackTrace();
                }
            }catch (Exception noRequestedSiteFound){
                ChangeCheckBox.adjustStyle(true,"nope",brandSearchAndSeries);
                webDriver.navigate().to(inputSearch.getText().trim());
                report.writeToFile(infoMessage, "Couldn't navigate to requested Site!");
                failedTestCases.writeToNamedFile(infoMessage, "Please check Brand Page: could not navigate to requested site", "FailAndReview");
                failedTestCases.writeToNamedFile("=================================TC 21","FailAndReview");

                noRequestedSiteFound.printStackTrace();
            }
        }catch (Exception noBrowserWorking){
            ChangeCheckBox.adjustStyle(true,"nope",brandSearchAndSeries);
            report.writeToFile(infoMessage, "unable to check! Browser not responding");
            failedTestCases.writeToNamedFile(infoMessage, "Please check Brand Page: browser not responding", "FailAndReview");
            failedTestCases.writeToNamedFile("=================================TC 21","FailAndReview");

            noBrowserWorking.printStackTrace();
        }
        report.writeToFile("=================================", "");

    }
}
