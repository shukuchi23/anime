package org.anime.threads;

import org.anime.model.SavePoint;
import org.anime.web.AbstractAnimeClient;
import org.anime.web.AnimeInterface;

import java.io.IOException;
import java.util.TimerTask;

import static org.anime.utils.StringUtils.log;

public class AutoSaveTask<T extends AbstractAnimeClient> extends TimerTask {
  private SavePoint savePoint;
  private T client;

  public AutoSaveTask(T client, SavePoint loadSavePoint) {
    this.client = client;
    savePoint = loadSavePoint;
  }


  @Override
  public void run() {

    savePoint = client.getAnimeInterface().getInfoAboutSeries(savePoint);
    try {
      log("autosave", "saved: " + savePoint);
      client.getRepository().insertOrUpdate(savePoint);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
