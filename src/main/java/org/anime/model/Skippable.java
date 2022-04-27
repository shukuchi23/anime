package org.anime.model;

import java.time.LocalTime;

public interface Skippable {
  void skipOpening(LocalTime startOpeningTime, LocalTime openingDuration);
  void skipEnding();
}
