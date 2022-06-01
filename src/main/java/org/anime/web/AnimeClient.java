package org.anime.web;

import org.anime.model.SavePoint;
import org.anime.repository.SavePointRepository;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import static org.anime.utils.StringUtils.log;

/**
 * @author Karimov Evgeniy
 * 13.05.2022
 */
@Component
public class AnimeClient {
  private SavePointRepository repository;
  private AnimeInterface animeInterface;
  private int secToAutosave;
  private WebElement skipButton = null;
  private boolean running;
  //  private SavePoint savePoint = null;
  private final Timer timer = new Timer(true);
  private AutoSaveTask task;
  private boolean firstTime;
  private boolean needSkipOpening;
  /*private final TimerTask task = new TimerTask() {
    @Override
    public void run() {
      try {
        synchronized (this) {
          if (needWait) {
            this.wait(500);
            log(Thread.currentThread().getName(), " is waiting...");
          }
        }
        SavePoint infoAboutSeries = animeInterface.getInfoAboutSeries(savePoint);
        if (infoAboutSeries != null) {
          log("autosave", "saved: " + infoAboutSeries);
          savePoint = infoAboutSeries;
        }
        repository.insertOrUpdate(savePoint);
        synchronized (needToStop) {
          if (needToStop)
            throw new BrowserWasClosedException("-- [autosave] - сlosed");
        }
      } catch (IOException e) {
        throw new PlayerException("что-то остановило плеер: " + e.getMessage());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  };*/

  public void startAutoSave() {
    running = true;
    task = new AutoSaveTask(savePoint);
    timer.scheduleAtFixedRate(task, 1000L, 1000L * secToAutosave);
  }

  public SavePoint stopAutoSave() {
    running = false;
    SavePoint savePoint = task.getSavePoint();
    if (savePoint == null) {
      // произошло переключение
      savePoint = this.savePoint;
      savePoint.setSeriesDuration(SavePoint.MyDuration.ZERO);
      savePoint.setSeriesNum(savePoint.getSeriesNum() + 1);
    }
    if (task != null)
      task.cancel();
    return savePoint;
  }

  public class AutoSaveTask extends TimerTask {
    private boolean running = false;
    private SavePoint savePoint;

    public AutoSaveTask(SavePoint loadSavePoint) {
      savePoint = loadSavePoint;
    }

    @Override
    public void run() {
      savePoint = animeInterface.getInfoAboutSeries(savePoint);
      try {
        log("autosave", "saved: " + savePoint);
        repository.insertOrUpdate(savePoint);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public synchronized SavePoint getSavePoint() {
      return this.savePoint;
    }
  }

  public synchronized void clickTheShit() {
    synchronized (skipButton) {
      skipButton.click();
    }
  }

  public void setSkipButton(WebElement element) {
    synchronized (skipButton) {
      skipButton = element;
    }
  }

  private SavePoint savePoint;

  private Supplier<WebElement> waitOpening = () -> {
    WebElement opening = animeInterface.getSkipOpeningButtonWhenVisible();
    System.out.println("-- opening was founded");
    return opening;
  };

  private Supplier<WebElement> waitEnding = () -> {
    WebElement ending = animeInterface.getSkipEndingButtonWhenVisible();
    System.out.println("-- ending was founded");
    return ending;
  };


  public void watch(SavePoint sp) {
    savePoint = sp;
    firstTime = true;
    while (true) {
      if (!seriesIsWatched)
        startSeries();
    }

  }

  private boolean seriesIsWatched;

  public void startSeries() {
    seriesIsWatched = true;
    initPlayer();
    preStart();
    start();
    CompletableFuture<Void> opening = null;
    if (needSkipOpening)
      opening = CompletableFuture.supplyAsync(waitOpening)
          .thenAccept(WebElement::click);

    CompletableFuture<WebElement> skipButton = CompletableFuture.supplyAsync(waitEnding);

    WebElement element;
    try {
      element = skipButton.get();
      if (opening != null && !opening.isDone())
        opening.cancel(true);
      preSwitchSeries();
      element.click();
      seriesIsWatched = false;
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }

    /*try {
      while (true) {
        if (!seriesWasSwitched)
          initPlayer();
        else
          preStart();
        start();
        animeInterface.startWithTime(savePoint.getSeriesDuration());
        synchronized (this){
          this.wait(5000);
          System.out.println("wait 5000");
        }
        getOpeningFuture()
            .thenAccept(v -> clickTheShit());
        if (!timerIsStart) {
          timer.scheduleAtFixedRate(task, 0, 1000L * secToAutosave);
          timerIsStart = true;
        }
        System.out.println("-- [autosave] - run ");
        getEndingFuture().thenAccept(v -> clickTheShit());
        postStart();
      }
    } catch (PlayerException | BrowserWasClosedException e) {
      timer.cancel();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }*/
  }


  // выбор качества и фул скрин
  public void start() {
    synchronized (this) {
      animeInterface.getPlayButton().click();
      try {
        this.wait(500);
        System.out.println("wait");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void initPlayer() {
    System.out.println("initPlayer");
    synchronized (savePoint) {
      if (firstTime)
        animeInterface.getClient().get(savePoint.getVideoUri());

      start();
      animeInterface.getFullScreenButton().click();
      List<WebElement> qualities = animeInterface.getQualityContainer();
      final WebElement maxQuality = findMaxQuality(qualities);
      if (!maxQuality.isSelected())
        maxQuality.click();
      start();
      needSkipOpening = animeInterface.startWithTime(savePoint.getSeriesDuration());
    }
  }


  public void preStart() {
    log("preStart");
    startAutoSave();

  }

  public synchronized void postStart() {
    log("postStart");

  }

  public void preSwitchSeries() {
    log("preSwitchSeries");
    SavePoint savePoint = stopAutoSave();
    if (savePoint != null && !savePoint.equals(this.savePoint)) {
      this.savePoint = savePoint;
      this.savePoint.setSeriesDuration(SavePoint.MyDuration.ZERO);
    }
  }

  private CompletableFuture<Void> getOpeningFuture() {
    return CompletableFuture.supplyAsync(waitOpening)
        .thenAccept(this::setSkipButton);
  }

  private CompletableFuture<Void> getEndingFuture() {
    return CompletableFuture.supplyAsync(waitEnding)
        .thenAccept(this::setSkipButton);
  }

  private WebElement findMaxQuality(List<WebElement> container) {
//    $$("li.vjs-menu-item")[1].children[0].innerText
    return container.get(1);
  }

  public AnimeClient() {

  }

  public AnimeClient(SavePointRepository repo, AnimeInterface animeInterface, int secToAutosave) {
    this.repository = repo;
    this.animeInterface = animeInterface;
    this.secToAutosave = secToAutosave;
  }

}
