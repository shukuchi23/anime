package org.anime.web;

import org.anime.config.DriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

@Component
@Lazy
public class WebClient implements AutoCloseable {
  protected static final String JS_INNER_TEXT_FORMAT = "return $('%s')[%d].innerText";
  protected static final String JS_LENGTH_FORMAT = "return $('%s').length";
  protected WebDriver webDriver;
  protected WebDriverWait waiter;

  public void get(String url) {
    webDriver.get(url);
  }

  public String getUrl() {
    return webDriver.getCurrentUrl();
  }


  public WebClient(MutableCapabilities option) {
    DriverConfig driverConfig = new DriverConfig();
    switch (option.getBrowserName()) {
      case CHROME:
        webDriver = driverConfig.chromeDriver();
        break;
      case FIREFOX:
        webDriver = driverConfig.firefoxDriver();
        break;
      default:
        throw new NotFoundException("На данный момент приложение поддерживает только firefox, opera, chrome." +
            "\nДрайвер " + option.getBrowserName() + "не найден");
    }
    waiter = new WebDriverWait(this.webDriver, 60 * 25);
  }

  public void openUri(String uri) {
    if (webDriver != null)
      webDriver.get(uri);

  }


  public WebDriver getWebDriver() {
    return webDriver;
  }

  public void setWebDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public WebDriverWait getWaiter() {
    return waiter;
  }

  public void setWaiter(WebDriverWait waiter) {
    this.waiter = waiter;
  }

  public String executeJsScript(String script) {
    final JavascriptExecutor webDriver = (JavascriptExecutor) this.webDriver;
    return (String) webDriver.executeScript(script);
  }

  public String getElementInnerText(String cssSelectorQuery) {
    final JavascriptExecutor webDriver = (JavascriptExecutor) this.webDriver;
    return (String) webDriver.executeScript(String.format(JS_INNER_TEXT_FORMAT, cssSelectorQuery, 0));
  }

  public boolean existObject(By selector) {
    try {
      webDriver.findElement(selector);
      return true;
    } catch (NoSuchElementException exception) {
      return false;
    }
  }

  public String getElementInnerTextWithWaiter(String cssSelectorQuery) {
    return waiter.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelectorQuery)))
        .getText();
  }


  public String[] getElementsInnerText(String cssSelectorQuery, Object[] indexes) {
    final JavascriptExecutor webDriver = (JavascriptExecutor) this.webDriver;
    final int length = indexes.length;
    final String[] rez = new String[length];
    for (int i = 0; i < length; i++) {
      rez[i] = (String) webDriver.executeScript(String.format(JS_INNER_TEXT_FORMAT, cssSelectorQuery, (Integer) indexes[i]));
    }
    return rez;
  }


  @Override
  public void close() throws Exception {
    webDriver.close();
  }
}
