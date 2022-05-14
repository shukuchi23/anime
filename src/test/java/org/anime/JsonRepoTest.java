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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author Karimov Evgeniy
 * 04.05.2022
 */
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class JsonRepoTest {
    @Autowired
    private File savePointJson;

    @Autowired
    private SavePointRepository savePointRepository;

    @Before
    public void setUp() throws Exception {
//    savePointRepository = new JsonSavePointRepository(savePointJson);
        final SavePoint test = new SavePoint(
                "test",
                1,
                new SavePoint.MyDuration(11, 11),
                "test_dub",
                "https://jut.su/saikou-no-ansatsusha/episode-1.html"
        );

        final SavePoint test1 = new SavePoint(
                "test1",
                1,
                new SavePoint.MyDuration(11, 11),
                "test_dub",
                "https://jut.su/oresuki/episode-1.html"
        );
        final SavePoint test2 = new SavePoint(
                "test2",
                10,
                new SavePoint.MyDuration(11, 11),
                "test_dub",
                "https://jut.su/oresuki/episode-1.html"
        );
        final SavePoint test3 = new SavePoint(
                "test3",
                1,
                new SavePoint.MyDuration(11, 11),
                "test_dub",
                "https://jut.su/oresuki/episode-1.html"
        );
        final SavePoint test4 = new SavePoint(
                "test4",
                1,
                new SavePoint.MyDuration(11, 11),
                "test_dub",
                "https://jut.su/oresuki/episode-1.html"
        );
        savePointRepository.insertOrUpdate(test);
        savePointRepository.insertOrUpdate(test1);
        savePointRepository.insertOrUpdate(test2);
        savePointRepository.insertOrUpdate(test3);
        savePointRepository.insertOrUpdate(test4);
    }

    @Test
    public void mustBeExistSavePoint() throws IOException {
        final SavePoint test = new SavePoint(
                "test",
                1,
                new SavePoint.MyDuration(11, 11),
                "test_dub",
                "https://jut.su/oresuki/episode-1.html"
        );

        Assert.assertEquals(test, savePointRepository.findOne(test.getTitleName()).get());
    }

    @Test
    public void updateExistSavePoint() throws IOException {
        SavePoint savePoint = new SavePoint(
                "test4",
                2,
                new SavePoint.MyDuration(11, 12),
                "new Dub",
                "https://jut.su/oresuki/episode-1.html");

        SavePoint oldSavePoint = savePointRepository.findAll().stream()
                .filter(sp -> sp.equals(savePoint))
                .findFirst()
                .get();

        savePointRepository.insertOrUpdate(savePoint);
        List<SavePoint> all = savePointRepository.findAll();
        Assert.assertNotEquals(oldSavePoint.getDubName(), savePoint.getDubName());
        Assert.assertNotEquals(oldSavePoint.getUpdateTime(), savePoint.getUpdateTime());
        Assert.assertEquals(oldSavePoint.getTitleName(), savePoint.getTitleName());
    }

    @After
    public void tearDown() throws Exception {
        savePointRepository.removeAll();
    }
}
