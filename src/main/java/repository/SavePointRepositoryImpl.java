package repository;

import exception.NotFoundException;
import model.SavePoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Karimov Evgeniy
 * 26.04.2022
 */
@Component
public class SavePointRepositoryImpl implements SavePointRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
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
        obj.getSeriesDuration().format(DateTimeFormatter.ofPattern("mm:ss")),
        obj.getDubName(),
        obj.getVideoUri());
  }

  @Override
  public List<SavePoint> findAll() throws NotFoundException {
    return findAll("DESC");
  }

  private static final String GET_ALL_BY_ORDER = "select * from SAVE_POINT order by ?;";

  @Override
  public List<SavePoint> findAll(String order) throws NotFoundException {
    final List<SavePoint> savePoints = jdbcTemplate.queryForList(GET_ALL_BY_ORDER, SavePoint.class, order);
    if (savePoints.isEmpty())
      throw new NotFoundException("Ты новенький?(никаких сохранений)");
    return savePoints;
  }

  private static final String FIND_BY_NAME = "select * from SAVE_POINT where title_name = ?";

  @Override
  public Optional<SavePoint> findOne(String titleName) throws NotFoundException {
    return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_NAME, ROW_MAPPER, titleName));
  }

  private static final String REMOVE_BY_NAME = "delete from SAVE_POINT where title_name = ?";

  @Override
  public boolean remove(String titleName) {
    return jdbcTemplate.update(REMOVE_BY_NAME, titleName) == 1;
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
        nextValue.getSeriesDuration(),
        nextValue.getDubName(),
        nextValue.getVideoUri(),
        new Date(),
        nextValue.getTitleName());
  }
}
