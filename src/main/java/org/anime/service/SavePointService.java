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
public class SavePointService {
  private final SavePointRepositoryImpl dao;

  @Autowired
  public SavePointService(SavePointRepositoryImpl dao) {
    this.dao = dao;
  }

  public List<SavePoint> findAll() throws NoSuchElementException {
    return findAll("DESC"); // по убыванию
  }

  public List<SavePoint> findAll(String order) throws NoSuchElementException {
    return dao.findAll(order);
  }

  // TODO: remove, и другую парашу
}
