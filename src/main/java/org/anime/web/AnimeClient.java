package org.anime.web;

import org.anime.exception.BrowserWasClosedException;
import org.anime.exception.PlayerException;
import org.anime.model.SavePoint;
import org.anime.repository.SavePointRepository;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Karimov Evgeniy
 * 13.05.2022
 */
@Component
public class AnimeClient {
  private SavePointRepository repository;
  private AnimeInterface animeInterface;
  private int secToAutosave;
  private Boolean needToStop = false;
//  private SavePoint savePoint = null;
  private final Timer timer = new Timer(true);

  private final TimerTask task = new TimerTask() {
    @Override
    public void run() {
      try {
        SavePoint infoAboutSeries = animeInterface.getInfoAboutSeries(savePoint);
        if (infoAboutSeries != null) {
          System.out.println("-- [autosave] - saved: " + infoAboutSeries);
          savePoint = infoAboutSeries;
        }
        repository.insertOrUpdate(savePoint);
        synchronized (needToStop) {
          if (needToStop)
            throw new BrowserWasClosedException("-- [autosave] - сlosed");
        }
      } catch (IOException e) {
        throw new PlayerException("что-то остановило плеер: " + e.getMessage());
      }
    }
  };

  public AnimeClient() {

  }

  public AnimeClient(SavePointRepository repo, AnimeInterface animeInterface, int secToAutosave) {
    this.repository = repo;
    this.animeInterface = animeInterface;
    this.secToAutosave = secToAutosave;
  }

  private SavePoint savePoint;

  public void startSeries(SavePoint sp) {
    savePoint = sp;
    try {
      while(true) {
        animeInterface.getClient().get(sp.getVideoUri());
        animeInterface.getPlayButton().click();
        animeInterface.getFullScreenButton().click();

        animeInterface.startWithTime(savePoint.getSeriesDuration());
        List<WebElement> qualities = animeInterface.getQualityContainer();
        final WebElement maxQuality = findMaxQuality(qualities);
        if (!maxQuality.isSelected()) {
          maxQuality.click();
        }
        timer.scheduleAtFixedRate(task, 0, 1000L * secToAutosave);
        System.out.println("-- [autosave] - run ");
        animeInterface.skipOpening();
        System.out.println("-- opening was skipped ");
        animeInterface.skipEnding();
        System.out.println("-- ending was skipped ");
      }
    } catch (PlayerException | BrowserWasClosedException e) {
      timer.cancel();
    }
  }
  public void pauseTimer(){

  }
  public void stopTimer() {
    synchronized (needToStop) {
      needToStop = true;
    }
  }

  private WebElement findMaxQuality(List<WebElement> container) {
//    $$("li.vjs-menu-item")[1].children[0].innerText
    return container.get(1);
  }
}
