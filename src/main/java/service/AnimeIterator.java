package service;

import org.openqa.selenium.WebElement;

import java.io.Closeable;
import java.util.Iterator;

public interface AnimeIterator extends Iterator<WebElement>, Closeable {
}
