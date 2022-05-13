package org.anime.web;

import org.anime.repository.SavePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Karimov Evgeniy
 * 13.05.2022
 */
@Component
public class AnimeClient {
  @Autowired
  private SavePointRepository repository;
  private AnimeInterface animeInterface;
  private Timer timer;
  private final TimerTask task = new TimerTask() {
    @Override
    public void run() {
      try {
        repository.insertOrUpdate(animeInterface.getInfoAboutSeries());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  };

}
