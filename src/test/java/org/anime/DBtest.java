package org.anime;

import org.anime.exception.NotFoundException;
import org.anime.model.SavePoint;
import org.anime.repository.SavePointRepositoryImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DBtest {

  @Autowired
  private JdbcTemplate testJdbcTemplate;

  private SavePointRepositoryImpl savePointRepository;

  @Before
  @Sql(scripts = "/savepoint.sql")
  public void setUp() throws Exception {
//    new AppHelper().initSavePoint();
    savePointRepository = new SavePointRepositoryImpl(testJdbcTemplate);
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
    savePointRepository.insert(test);
    savePointRepository.insert(test1);
    savePointRepository.insert(test2);
    savePointRepository.insert(test3);
    savePointRepository.insert(test4);
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

    Assert.assertEquals(test, savePointRepository.findOne(test.getTitleName()).get());
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
    savePointRepository.findOne(test.getTitleName());
  }

  @Test(expected = NotFoundException.class)
  public void notFoundSavePoints() {
    savePointRepository.removeAll();
    final List<SavePoint> all = savePointRepository.findAll();
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
    final SavePoint savePoint = savePointRepository.findOne(test.getTitleName()).get();

    savePointRepository.update(test);
    final SavePoint byPk = savePointRepository.findOne(test.getTitleName()).get();
    Assert.assertEquals(byPk.getTitleName(), test.getTitleName());
    Assert.assertEquals(byPk.getVideoUri(), test.getVideoUri());
    Assert.assertEquals(byPk.getSeriesDuration(), test.getSeriesDuration());
    Assert.assertEquals(byPk.getDubName(), test.getDubName());
    Assert.assertEquals(byPk.getSeriesNum(), test.getSeriesNum());
  }

  @After
  public void tearDown() throws Exception {
    savePointRepository.removeAll();
  }
}
