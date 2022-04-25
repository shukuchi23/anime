package config;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {
  protected static FileInputStream fileInputStream;
  protected static Properties properties;
  static {
    try {
      fileInputStream = new FileInputStream("src/main/resources/application.properties");
      properties = new Properties();
      properties.load(fileInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      if (fileInputStream != null){
        try {
          fileInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
  public static WebDriver getDriver(String driverName){
    switch (driverName){
      case "firefox":
        return new FirefoxDriver(new FirefoxOptions());
      case "opera_driver":
      case "chrome_driver":
        return new ChromeDriver(new ChromeOptions());
      default:
        throw new NotFoundException("На данный момент приложение поддерживает только firefox, opera, chrome." +
            "\nДрайвер " + driverName + "не найден");
    }
  }
  public static String getProperty(String key){
    return properties.getProperty(key);
  }
}
