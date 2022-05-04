package org.anime.repository;

import org.anime.exception.NotFoundException;
import org.anime.model.SavePoint;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Karimov Evgeniy
 * 26.04.2022
 */
//@Component
public class SavePointRepositoryImpl implements SavePointRepository, Droppable {
  private static final String DROP_SQL = "drop table %s;";
  private JdbcTemplate jdbcTemplate;

  public SavePointRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private static final String INSERT_SQL =
      "insert into SAVE_POINT (title_name, series_num, series_duration, dub_name, source_uri) values(?, ?, ?, ?, ?);";

  @Override
  public void insert(SavePoint obj) {
    jdbcTemplate.update(INSERT_SQL,
        obj.getTitleName(),
        obj.getSeriesNum(),
//        obj.getSeriesDuration().format(DateTimeFormatter.ofPattern("mm:ss")),
        obj.getDubName(),
        obj.getVideoUri());
  }

  @Override
  public List<SavePoint> findAll() throws NotFoundException {
    final List<SavePoint> savePoints = jdbcTemplate.query(FIND_ALL, ROW_MAPPER);
    if (savePoints.isEmpty())
      throw new NotFoundException("Ты новенький?(никаких сохранений)");
    return savePoints;
  }

  private static final String FIND_ALL= "select * from SAVE_POINT order by (UPDATE_TIME) desc;";
  private static final String FIND_BY_NAME = "select * from SAVE_POINT where title_name = ?;";

  @Override
  public Optional<SavePoint> findOne(String titleName) throws NotFoundException {
    try {
      final Optional<SavePoint> savePoint = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME, ROW_MAPPER, titleName));
      return savePoint;
    } catch (Exception e) {
      throw new NotFoundException(e);
    }
  }

  private static final String REMOVE_BY_NAME = "delete from SAVE_POINT where title_name = ?;";
  private static final String REMOVE_ALL = "delete from SAVE_POINT;";

  @Override
  public boolean remove(String titleName) {
    return jdbcTemplate.update(REMOVE_BY_NAME, titleName) == 1;
  }

  @Override
  public boolean removeAll() {
    return jdbcTemplate.update(REMOVE_ALL) != 0;
  }

  private static final String UPDATE_SQL = "update SAVE_POINT set " +
      "series_num = ?," +
      "series_duration = ?," +
      "dub_name = ?," +
      "source_uri = ?," +
      "update_time = ? where title_name = ?;";

  @Override
  public void update(SavePoint nextValue) throws NotFoundException {
    jdbcTemplate.update(UPDATE_SQL,
        nextValue.getSeriesNum(),
//        nextValue.getSeriesDuration().format(DateTimeFormatter.ofPattern("mm:ss")),
        nextValue.getDubName(),
        nextValue.getVideoUri(),
        new Date(),
        nextValue.getTitleName());
  }

  private static final String TABLE_NAME = "SAVE_POINT";

  @Override
  public void dropTable() {
    jdbcTemplate.execute(String.format(DROP_SQL, TABLE_NAME));
  }
}
