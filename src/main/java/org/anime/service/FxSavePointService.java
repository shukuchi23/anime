package org.anime.service;

import org.anime.fxcomponent.FxComponentFactory;
import org.anime.fxcomponent.FxSavePoint;
import org.anime.utils.IconProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.anime.repository.SavePointRepositoryImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author Karimov Evgeniy
 * 26.04.2022
 */
//@Component
public class FxSavePointService {
  /* Класс предоставляющий функционал над объектами класса FxSavePoint.
  * Примерный функционал:
  * * найти все точки сохранения;
  * * удалить заданную точку;
  * *
  * *
  * */
  private final SavePointRepositoryImpl repository;

//  @Autowired
  public FxSavePointService(SavePointRepositoryImpl dao) {
    this.repository = dao;
  }

  @Bean
  public List<FxSavePoint> savePoints() throws NoSuchElementException {
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
