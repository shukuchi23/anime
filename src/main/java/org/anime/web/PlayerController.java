package org.anime.web;

import org.anime.model.SavePoint;
import org.openqa.selenium.WebElement;

public class PlayerController extends Thread implements AnimeInterface {
    private final int secondToSave;
    private SavePoint savePoint;
    private AnimeInterface animeInterface;

    PlayerController(int secondToSave, SavePoint savePoint){
        this.secondToSave = secondToSave;
        this.savePoint = savePoint;

    }

    @Override
    public void run() {

    }

    @Override
    public WebElement getQualityContainer() {
        return null;
    }

    @Override
    public WebElement getPlayButton() {
        return null;
    }

    @Override
    public WebElement nextSeries() {
        return null;
    }

    @Override
    public SavePoint getInfoAboutSeries() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
