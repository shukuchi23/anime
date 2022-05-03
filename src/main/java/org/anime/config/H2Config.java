package org.anime.config;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.jdbcx.JdbcDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:./h2.properties")
public class H2Config {

  @Autowired
  private Environment environment;

  @Bean
  public DataSource dataSource() {
    JdbcDataSource ds = new JdbcDataSource();
    ds.setURL(environment.getProperty("spring.datasource.history_db"));
    ds.setUser("sa");
    return ds;
  }
  @Bean
  public DataSource testDataSource() {
    JdbcDataSource ds = new JdbcDataSource();
    ds.setURL(environment.getProperty("spring.datasource.test_db"));
    ds.setUser("sa");
    return ds;
  }

  @Bean
  @Autowired
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  @Autowired
  public JdbcTemplate testJdbcTemplate(DataSource testDataSource) {
    return new JdbcTemplate(testDataSource);
  }

  protected static FileInputStream fileInputStream;
  protected static Properties properties;

  static {
    try {
      fileInputStream = new FileInputStream("src/main/resources/h2.properties");
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
