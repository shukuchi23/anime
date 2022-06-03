package org.anime.web;

import org.anime.model.SavePoint;
import org.anime.repository.SavePointRepository;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public abstract class AbstractAnimeClient<T extends AnimeInterface> {
  private SavePointRepository repository;
  protected T animeInterface;
  private boolean firstTime;
  private boolean seriesIsWatched;
  private boolean isLastEpisode;
  private boolean needSkipOpening;

  private SavePoint savePoint;
  private final Timer timer = new Timer(true);


  private Supplier< ? extends Object> waitOpening;
//  private Supplier< ? extends Object> waitEnding;


  public void watch(SavePoint sp){
    savePoint = sp;
    firstTime = true;

    while(true){
      if (!seriesIsWatched)
        startSeries();
    }

  }
  public void startSeries(){
    seriesIsWatched = true;
    initPlayer();
    preStart();
    start();

    CompletableFuture<Void> opening = null;
    // todo: реализовать

    if (needSkipOpening)
      waitOpeningAndSkip();
      /*opening = CompletableFuture.supplyAsync(waitOpening)
          .thenAccept(WebElement::click);*/

    if (!isLastEpisode){
      waitEnding();

      if (opening != null && !opening.isDone())
        opening.cancel(true);
      preSwitchSeries();
      seriesIsWatched = false;

      skipEnding();
      postSwitchSeries();
      // todo: реализовать скип эндинга
      /*CompletableFuture<WebElement> skipButton = CompletableFuture.supplyAsync(waitEnding);
      WebElement element;
      try {
        element = skipButton.get();
        if (opening != null && !opening.isDone())
          opening.cancel(true);
        preSwitchSeries();
        seriesIsWatched = false;
        element.click();
        postSwitchSeries();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }*/
    }
  }

  protected abstract void skipEnding();

  protected abstract void waitOpeningAndSkip();
  protected abstract void waitEnding();

  protected abstract  void postSwitchSeries();

  protected abstract void preSwitchSeries();

  protected abstract void start();

  protected abstract void preStart();


  public T getAnimeInterface() {
    return animeInterface;
  }

  public SavePointRepository getRepository() {
    return repository;
  }

  protected abstract void initPlayer();

  protected WebElement findMaxQuality(List<WebElement> container){
    return container.get(1);
  }
}
