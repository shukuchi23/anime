package org.anime.web;

import javax.annotation.Nullable;
import java.time.LocalTime;

public interface Skippable {
  void skipOpening(LocalTime startOpeningTime, LocalTime openingDuration);
  void skipEnding();
}
