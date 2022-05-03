package org.anime.repository;

import org.anime.exception.NotFoundException;
import org.anime.web.SavePoint;
import org.springframework.jdbc.core.RowMapper;
import org.anime.utils.TypeUtils;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Karimov Evgeniy
 * 26.04.2022
 */
public interface SavePointRepository {

  RowMapper<SavePoint> ROW_MAPPER = (ResultSet rs, int rowNum) -> {return new SavePoint(
      rs.getString("title_name"),
      rs.getInt("series_num"),
      TypeUtils.localTimeFromString(rs.getString("series_duration")),
      rs.getString("dub_name"),
      rs.getDate("update_time"),
      rs.getString("source_uri")
  );
  };

  void insert(SavePoint obj);

  List<SavePoint> findAll() throws NotFoundException;

  Optional<SavePoint> findOne(String titleName) throws NotFoundException;

  boolean remove(String titleName);
  boolean removeAll();

  void update(SavePoint nextValue) throws NotFoundException;
}
