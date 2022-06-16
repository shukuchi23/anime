package org.anime;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;

/**
 * @author Karimov Evgeniy
 * 26.04.2022
 */
public abstract class AbstractJavaFxApplicationSupport extends Application {
  private static String[] savedArgs;

  protected ConfigurableApplicationContext context;

  @Override
  public void init() throws Exception {
    context = SpringApplication.run(getClass(), savedArgs);
    context.getAutowireCapableBeanFactory().autowireBean(this);
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    context.close();
  }

  protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> clazz, String[] args) {
    AbstractJavaFxApplicationSupport.savedArgs = args;
    Application.launch(clazz, args);
  }
}
