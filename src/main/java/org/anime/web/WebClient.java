package org.anime.web;

import org.anime.config.DriverConfig;
import org.anime.model.MyOption;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class WebClient implements AutoCloseable {
    protected WebDriver webDriver;
    protected WebDriverWait waiter;
    public void get(String url){
        webDriver.get(url);
    }
    public WebClient(MyOption option){
        DriverConfig driverConfig = new DriverConfig();
        switch (option.getBrowserType()){
            case CHROME:
                webDriver = driverConfig.chromeDriver();
                break;
            case FIREFOX:
                webDriver = driverConfig.firefoxDriver();
                break;
            default:
                throw new NotFoundException("На данный момент приложение поддерживает только firefox, opera, chrome." +
                    "\nДрайвер " + option.getBrowserType().getBrowserName() + "не найден");
        }
        waiter = new WebDriverWait(this.webDriver, 60 * 25);
    }

    public void openUri(String uri){
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

    @Override
    public void close() throws Exception {
        webDriver.close();
    }
}
