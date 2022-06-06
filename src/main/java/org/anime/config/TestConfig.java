package org.anime.config;

import org.anime.model.MyOption;
import org.anime.web.AnimeInterface;
import org.anime.web.JutsuPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.File;
import java.util.Objects;

/**
 * @author Karimov Evgeniy
 * 03.06.2022
 */
@Configuration
@PropertySource("classpath:test.properties")
@Profile("test")
public class TestConfig {

  @Autowired
  private Environment environment;

  @Bean
  public File savePointJson(){
    return new File(Objects.requireNonNull(environment.getProperty("json_source")));
  }

  @Bean
  public AnimeInterface jutsuPlayer(){
    return new JutsuPlayer();
  }
/*
  private File getJsonByName(String filename){
    return new File("json/" + filename);
  }*/

  @Bean
  public MyOption defaultOption(){
    return MyOption.getInstance(
        environment.getProperty("myapp.default_browser", MyOption.BrowserType.class),
        Boolean.TRUE.equals(environment.getProperty("myapp.skip_opening", Boolean.class)),
        Boolean.TRUE.equals(environment.getProperty("myapp.skip_ending", Boolean.class))
    );
  }
}
