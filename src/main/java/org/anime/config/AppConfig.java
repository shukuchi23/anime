package org.anime.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
  @Autowired
  private Environment environment;
  protected static FileInputStream fileInputStream;
  protected static Properties properties;
  static {
    try {
      fileInputStream = new FileInputStream("src/main/resources/application.properties");
      properties = new Properties();
      properties.load(fileInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fileInputStream != null) {
        try {
          fileInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static String getProperty(String key) {
    return properties.getProperty(key);
  }
}
