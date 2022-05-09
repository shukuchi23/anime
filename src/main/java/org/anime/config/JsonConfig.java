package org.anime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @author Karimov Evgeniy
 * 04.05.2022
 */
@Configuration
public class JsonConfig {
  @Bean
  public File savePointJson(){
    return getJsonByName("save_point.json");
  }


  @Bean
  public File settingJson(){
    return getJsonByName("setting.json");
  }
  private File getJsonByName(String filename){
    return new File("json/" + filename);
  }
}
