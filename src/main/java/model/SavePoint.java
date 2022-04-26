package model;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class SavePoint {

  private String titleName;
  private int seriesNum;
  private LocalTime seriesDuration;
  private String dubName;
  private Date updateTime;
  private String videoUri; // https://jut.su/naruto...

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SavePoint savePoint = (SavePoint) o;
    return seriesNum == savePoint.seriesNum && titleName.equals(savePoint.titleName) && seriesDuration.equals(savePoint.seriesDuration) && Objects.equals(dubName, savePoint.dubName) && videoUri.equals(savePoint.videoUri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titleName, seriesNum, seriesDuration, dubName, videoUri);
  }

  public SavePoint(String titleName, int seriesNum, LocalTime seriesDuration, String dubName, Date updateTime, String videoUri) {
    this.titleName = titleName;
    this.seriesNum = seriesNum;
    this.seriesDuration = seriesDuration;
    this.dubName = dubName;
    this.updateTime = updateTime;
    this.videoUri = videoUri;
  }

  public SavePoint(String titleName, int seriesNum, LocalTime seriesDuration, String dubName, String videoUri) {
    this.titleName = titleName;
    this.seriesNum = seriesNum;
    this.seriesDuration = seriesDuration;
    this.dubName = dubName;
    this.videoUri = videoUri;
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

  public LocalTime getSeriesDuration() {
    return seriesDuration;
  }

  public void setSeriesDuration(LocalTime seriesDuration) {
    this.seriesDuration = seriesDuration;
  }

  public String getDubName() {
    return dubName;
  }

  public void setDubName(String dubName) {
    this.dubName = dubName;
  }

  public String getVideoUri() {
    return videoUri;
  }

  public void setVideoUri(String videoUri) {
    this.videoUri = videoUri;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  //
//
//  String getTitleName();
//  String getSeries();
//  String getWatchedSeriesTime();
//  String getSeriesTime();

}
