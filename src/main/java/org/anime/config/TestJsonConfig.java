package org.anime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;

@Configuration
@Profile("test")
public class TestJsonConfig {

    @Bean
    public File savePointJson(){
        return getJsonByName("test.json");
    }


    private File getJsonByName(String filename){
        return new File("json/" + filename);
    }

}
