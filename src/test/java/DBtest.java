import config.H2Config;
import repository.SavePointRepositoryImpl;
import exception.NotFoundException;
import model.SavePoint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalTime;

public class DBtest {
  private SavePointRepositoryImpl dao;

  @Before
  public void setUp() throws Exception {
//    dao = new SavePointRepositoryImpl(H2Config.getProperty("test_db"));
    final SavePoint test = new SavePoint(
        "test",
        1,
        LocalTime.of(0, 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    final SavePoint test1 = new SavePoint(
        "test1",
        1,
        LocalTime.of(1, 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    final SavePoint test2 = new SavePoint(
        "test2",
        10,
        LocalTime.of(0, 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    final SavePoint test3 = new SavePoint(
        "test3",
        1,
        LocalTime.of(0, 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    final SavePoint test4 = new SavePoint(
        "test4",
        1,
        LocalTime.of(0, 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    dao.insert(test);
    dao.insert(test1);
    dao.insert(test2);
    dao.insert(test3);
    dao.insert(test4);
  }

  @BeforeClass
  public static void beforeClass() throws Exception {

  }

  /*@Test
  public void firstTest() {
    final AppHelper appHelper = new AppHelper();
    appHelper.initSavePoint(H2Config.getProperty("test_db"));

    try (Connection db = DriverManager.getConnection(H2Config.getProperty("test_db"))) {
      try (Statement dataQuery = db.createStatement()) {
        dataQuery.execute(CREATE_QUERY);
        dataQuery.execute(DATA_QUERY);
      }

      try (PreparedStatement query = db.prepareStatement("SELECT * FROM EXAMPLE");
           ResultSet rs = query.executeQuery()) {

        while (rs.next()) {
          System.out.println(String.format("%s, %s!",
              rs.getString(1),
              rs.getString("TARGET")));
        }
      }
    } catch (SQLException ex) {
      System.out.println("Database connection failure: "
          + ex.getMessage());
    }
  }*/

  @Test
  public void mustBeExistSavePoint() {
    final SavePoint test = new SavePoint(
        "test",
        1,
        LocalTime.of(0, 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );

    Assert.assertEquals(test, dao.findOne(test.getTitleName()).get());
  }

  @Test(expected = NotFoundException.class)
  public void notFoundSavePoint() {
    final SavePoint test = new SavePoint(
        "test10",
        1,
        LocalTime.of(0, 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    dao.findOne(test.getTitleName());
  }

  @Test(expected = NotFoundException.class)
  public void notFoundSavePoints() {
    dao.findAll();
  }

  @Test
  public void canUpdate() {
    final SavePoint test = new SavePoint(
        "test",
        5,
        LocalTime.of(0, 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    final SavePoint savePoint = dao.findOne(test.getTitleName()).get();

    dao.update(test);
    final SavePoint byPk = dao.findOne(test.getTitleName()).get();
    Assert.assertEquals(byPk.getTitleName(), test.getTitleName());
    Assert.assertEquals(byPk.getVideoUri(), test.getVideoUri());
    Assert.assertEquals(byPk.getSeriesDuration(), test.getSeriesDuration());
    Assert.assertEquals(byPk.getDubName(), test.getDubName());
    Assert.assertEquals(byPk.getSeriesNum(), test.getSeriesNum());
  }
}
