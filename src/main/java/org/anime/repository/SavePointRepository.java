package org.anime.repository;

import org.anime.exception.NotFoundException;
import org.anime.model.SavePoint;
import org.springframework.jdbc.core.RowMapper;
import org.anime.utils.TypeUtils;

import java.io.IOException;
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
      new SavePoint.MyDuration(0, 0),
//      TypeUtils.localTimeFromString(rs.getString("series_duration")),
      rs.getString("dub_name"),
      rs.getDate("update_time"),
      rs.getString("source_uri")
  );
  };

  void insert(SavePoint obj) throws IOException;

  List<SavePoint> findAll() throws NotFoundException, IOException;

  Optional<SavePoint> findOne(String titleName) throws NotFoundException, IOException;

  boolean remove(String titleName) throws IOException;
  boolean removeAll() throws IOException;
  void insertOrUpdate(SavePoint obj) throws IOException;
}
