package com.naukri.automation;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NaukriUpdate {

    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        System.out.println("════════════════════════════════════");
        System.out.println("🚀 Naukri Automation Started");
        System.out.println("════════════════════════════════════");
        
        try {

            login();

            updateProfile();

            uploadResume();

            logout();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static void login() {

        driver.get(Config.LOGIN_URL);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("usernameField"))).sendKeys(Config.USERNAME);

        driver.findElement(By.id("passwordField"))
                .sendKeys(Config.PASSWORD);

        driver.findElement(
                By.xpath("//*[@type='submit' and normalize-space()='Login']"))
                .click();

        System.out.println("✅ Login Successful");
        
        System.out.println("════════════════════════════════════");
        System.out.println("🚀 Naukri Automation Ended");
        System.out.println("════════════════════════════════════");
    }

    public static void updateProfile() throws Exception {

        Thread.sleep(5000);

        driver.findElement(
                By.xpath("//*[contains(@class,'view-profile')]//a"))
                .click();

        Thread.sleep(3000);

        driver.findElement(
                By.xpath("(//*[contains(@class,'icon edit')])[1]"))
                .click();

        System.out.println("📝 Updating Profile Details...");
//        WebElement mobile = 
//                wait.until(ExpectedConditions.visibilityOfElementLocated(
//                        By.xpath("//*[@name='mobile']")));
//
//        mobile.clear();
//        mobile.sendKeys(Config.MOBILE);
// 
        WebElement saveBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("saveBasicDetailsBtn")));

        saveBtn.click();
 
        // Wait for success popup/cross icon
        WebElement closeIcon = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/div[6]/div[2]/div[1]/span")));
        
        System.out.println("✅ Profile Updated Successfully");
        
        closeIcon.click();
        
        System.out.println("❎ Success Popup Closed");
        
    }

    public static void uploadResume() throws Exception {

        driver.get(Config.PROFILE_URL);

        Thread.sleep(3000);
        
        System.out.println("📄 Resume Upload Started...");

        WebElement upload =
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.id("attachCV")));

        upload.sendKeys(Config.RESUME_PATH);

        System.out.println("✅ Resume Uploaded Successfully");
    }

    public static void logout() {

        try {

            driver.findElement(
                    By.xpath("//img[@alt='naukri user profile img']"))
                    .click();
            
            driver.findElement(
                    By.xpath("//a[normalize-space()='Logout']"))
                    .click();

            System.out.println("✅ Logout Successful");

        } catch (Exception e) {
            System.out.println("Logout Failed");
        }
    }
}
