package model;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;

public class SavePoint {

  private String titleName;
  private int seriesNum;
  private Duration seriesDuration;
  private String dubName;
  private LocalDateTime updateTime;
  private String sourceURI; // https://jut.su/

  public SavePoint(String titleName, int seriesNum, Duration seriesDuration, String dubName, String sourceURI) {
    this.titleName = titleName;
    this.seriesNum = seriesNum;
    this.seriesDuration = seriesDuration;
    this.dubName = dubName;
    this.sourceURI = sourceURI;
    updateTime = LocalDateTime.now(Clock.systemDefaultZone());
  }

  public String getTitleName() {
    return titleName;
  }

  public void setTitleName(String titleName) {
    this.titleName = titleName;
  }

  public int getSeriesNum() {
    return seriesNum;
  }

  public void setSeriesNum(int seriesNum) {
    this.seriesNum = seriesNum;
  }

  public Duration getSeriesDuration() {
    return seriesDuration;
  }

  public void setSeriesDuration(Duration seriesDuration) {
    this.seriesDuration = seriesDuration;
  }

  public String getDubName() {
    return dubName;
  }

  public void setDubName(String dubName) {
    this.dubName = dubName;
  }

  public String getSourceURI() {
    return sourceURI;
  }

  public void setSourceURI(String sourceURI) {
    this.sourceURI = sourceURI;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  //
//
//  String getTitleName();
//  String getSeries();
//  String getWatchedSeriesTime();
//  String getSeriesTime();

}
