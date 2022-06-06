package org.anime.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:drivers.properties")
public class DriverConfig {

  @Autowired
  private Environment environment;

  @Bean
  public WebDriver chromeDriver(){
    // строчка, чтобы убрать мешающий банер
    return new ChromeDriver(chromeOption());
  }

  @Bean
  public MutableCapabilities chromeOption(){
    final ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
    return chromeOptions;
  }



  @Bean
  public WebDriver firefoxDriver(){
    return new FirefoxDriver(new FirefoxOptions());
  }



/*
  protected static FileInputStream fileInputStream;
  protected static Properties properties;
  static {
    try {
      fileInputStream = new FileInputStream("src/main/resources/drivers.properties");
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
      case "opera":
      case "chrome":
        return new ChromeDriver(new ChromeOptions());
      default:
        throw new NotFoundException("На данный момент приложение поддерживает только firefox, opera, chrome." +
            "\nДрайвер " + driverName + "не найден");
    }
  }
  public static String getProperty(String key){
    return properties.getProperty(key);
  }*/
}
