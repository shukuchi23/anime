package org.anime;

import org.anime.model.SavePoint;
import org.anime.repository.JsonSavePointRepository;
import org.anime.repository.SavePointRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * @author Karimov Evgeniy
 * 04.05.2022
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JsonRepoTest {
  @Autowired
  @Qualifier("testJson")
  private File source;

  private SavePointRepository savePointRepository;

  @Before
  public void setUp() throws Exception {
    savePointRepository = new JsonSavePointRepository(source);
    final SavePoint test = new SavePoint(
        "test",
        1,
        new SavePoint.MyDuration( 11, 11),
        "test_dub",
        "https://jut.su/saikou-no-ansatsusha/episode-1.html"
    );

    final SavePoint test1 = new SavePoint(
        "test1",
        1,
        new SavePoint.MyDuration( 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    final SavePoint test2 = new SavePoint(
        "test2",
        10,
        new SavePoint.MyDuration( 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    final SavePoint test3 = new SavePoint(
        "test3",
        1,
        new SavePoint.MyDuration( 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    final SavePoint test4 = new SavePoint(
        "test4",
        1,
        new SavePoint.MyDuration( 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );
    savePointRepository.insert(test);
    savePointRepository.update(test1);
    savePointRepository.update(test2);
    savePointRepository.update(test3);
    savePointRepository.update(test4);
  }

  @Test
  public void mustBeExistSavePoint() throws IOException {
    final SavePoint test = new SavePoint(
        "test",
        1,
        new SavePoint.MyDuration( 11, 11),
        "test_dub",
        "https://jut.su/oresuki/episode-1.html"
    );

    Assert.assertEquals(test, savePointRepository.findOne(test.getTitleName()).get());
  }
  @After
  public void tearDown() throws Exception {
    savePointRepository.removeAll();
    savePointRepository = null;
  }
}
