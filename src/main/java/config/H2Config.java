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

public class H2Config {
  protected static FileInputStream fileInputStream;
  protected static Properties properties;
  static {
    try {
      fileInputStream = new FileInputStream("src/main/resources/h2.properties");
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
  public static String getProperty(String key){
    return properties.getProperty(key);
  }
}
