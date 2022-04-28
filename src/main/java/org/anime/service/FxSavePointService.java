package org.anime.service;

import org.anime.model.SavePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.anime.repository.SavePointRepositoryImpl;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Karimov Evgeniy
 * 26.04.2022
 */
@Component
public class FxSavePointService {
  /* Класс предоставляющий функционал над объектами класса FxSavePoint.
  * Примерный функционал:
  * * найти все точки сохранения;
  * * удалить заданную точку;
  * *
  * *
  * */
  private final SavePointRepositoryImpl repository;

  @Autowired
  public FxSavePointService(SavePointRepositoryImpl dao) {
    this.repository = dao;
  }

  public List<SavePoint> findAll() throws NoSuchElementException {
    return repository.findAll(); // по убыванию
  }


  // TODO: remove, и другую парашу
}
