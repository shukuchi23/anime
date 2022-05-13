package org.anime.model;

import javax.annotation.Nullable;

public class MyOption {
    private BrowserType browserType;
    private boolean skipOpening;
    private boolean skipEnding;
    private static MyOption instance;
    public static MyOption getInstance(BrowserType browserType,
                                       boolean skipOpening,
                                       boolean skipEnding){
        if (instance == null)
            instance = new MyOption(browserType, skipOpening, skipEnding);
        return instance;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public void setBrowserType(BrowserType browserType) {
        this.browserType = browserType;
    }

    public boolean isSkipOpening() {
        return skipOpening;
    }

    public void setSkipOpening(boolean skipOpening) {
        this.skipOpening = skipOpening;
    }

    public boolean isSkipEnding() {
        return skipEnding;
    }

    public void setSkipEnding(boolean skipEnding) {
        this.skipEnding = skipEnding;
    }

    public void reset(@Nullable BrowserType browserType,
                      @Nullable Boolean skipOpening,
                      @Nullable Boolean skipEnding){
        if (browserType != null)
            instance.browserType = browserType;
        if (skipEnding != null)
            instance.skipEnding = skipEnding;
        if (skipOpening != null)
            instance.skipOpening = skipOpening;
    }


    private MyOption(BrowserType browserType,
                     boolean skipOpening,
                     boolean skipEnding)
    {
        this.browserType = browserType;
        this.skipOpening = skipOpening;
        this.skipEnding = skipEnding;
    }

    public enum BrowserType{
        FIREFOX("firefox"),
        CHROME("chrome");
        private String browserName;
        BrowserType(String browserName){
            this.browserName = browserName;
        }
        public String getBrowserName() {
            return browserName;
        }
    }

}
