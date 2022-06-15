package org.anime.config;

import org.anime.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@Profile("prod")
public class AppConfig {
  private static final String PATH = "src/main/resources/application.properties";
  @Autowired
  private Environment environment;

  /*@Bean
  public MyOption defaultOption(){
    return MyOption.getInstance(
        environment.getProperty("myapp.default_browser", MyOption.BrowserType.class),
        Boolean.TRUE.equals(environment.getProperty("myapp.skip_opening", Boolean.class)),
        Boolean.TRUE.equals(environment.getProperty("myapp.skip_ending", Boolean.class))
        );
  }*/
/*
  @Bean
  public MyOption installedOption(){
    return MyOption.getInstance(
        environment.getProperty("myapp.default_browser", MyOption.BrowserType.class),
        Boolean.TRUE.equals(environment.getProperty("myapp.skip_opening", Boolean.class)),
        Boolean.TRUE.equals(environment.getProperty("myapp.skip_ending", Boolean.class))
    );
  }*/

  protected static FileInputStream fileInputStream;
  protected static Properties properties;

  static {
    init();
  }

  private static void init() {
    try  {
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

  public static boolean putProperty(String key, String value) {
    properties.setProperty(key, value);
    try (FileOutputStream stream = new FileOutputStream(PATH)) {
      StringUtils.log("AppConfig", "change properties");
      properties.store(stream, null);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    init();
    return true;
  }
}
