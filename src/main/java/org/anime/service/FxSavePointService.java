package org.anime.service;

import org.anime.fxcomponent.FxComponentFactory;
import org.anime.fxcomponent.FxSavePoint;
import org.anime.repository.SavePointRepository;
import org.anime.utils.IconProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author Karimov Evgeniy
 * 26.04.2022
 */
@Component("fxSavePointService")
public class FxSavePointService {
  /* Класс предоставляющий функционал над объектами класса FxSavePoint.
  * Примерный функционал:
  * * найти все точки сохранения;
  * * удалить заданную точку;
  * *
  * *
  * */
  private SavePointRepository repository;

  @Autowired
  public FxSavePointService(SavePointRepository repository) {
    this.repository = repository;
  }

  @Bean
  public List<FxSavePoint> savePoints() throws NoSuchElementException, IOException {
    return repository.findAll()
            .stream()
            .map(sp -> {
                return FxComponentFactory.createFxSavePoint(
                        IconProvider.iconNameFromLink(sp.getVideoUri()),
                        sp
                );
            })
            .collect(Collectors.toList());
//            ; // по убыванию
  }
  // TODO: remove, и другую парашу
}
