package org.anime.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;
import java.util.Objects;

public class SavePoint implements Comparable {

  private String titleName;
  private int seriesNum;
  @JsonSerialize(using = ToStringSerializer.class)
  private MyDuration seriesDuration;
  private String dubName;
  private String videoUri; // https://jut.su/naruto...
  @JsonSerialize(using = ToStringSerializer.class)
  private Date updateTime;

  public SavePoint() {

  }

  @Override
  public int compareTo(Object o) {
    SavePoint o1 = (SavePoint) o;
    return getUpdateTime().compareTo(o1.getUpdateTime());
  }

  public static class MyDuration {
    private int minutes;
    private int seconds;

    @Override
    public String toString() {
      return String.format("%02d:%02d", minutes, seconds);
    }

    public int getMinutes() {
      return minutes;
    }

    public void setMinutes(int minutes) {
      this.minutes = minutes;
    }

    public int getSeconds() {
      return seconds;
    }

    public void setSeconds(int seconds) {
      this.seconds = seconds;
    }


    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      MyDuration that = (MyDuration) o;
      return minutes == that.minutes && seconds == that.seconds;
    }

    @Override
    public int hashCode() {
      return Objects.hash(minutes, seconds);
    }

    public MyDuration(String str) {
      final String[] split = str.split(":");
      minutes = Integer.parseInt(split[0]);
      seconds = Integer.parseInt(split[1]);
    }

    public MyDuration(int minutes, int seconds) {
      this.minutes = minutes;
      this.seconds = seconds;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SavePoint savePoint = (SavePoint) o;
    return titleName.equals(savePoint.titleName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titleName);
  }

  public SavePoint(String titleName, int seriesNum, MyDuration seriesDuration, String dubName, Date updateTime, String videoUri) {
    this.titleName = titleName;
    this.seriesNum = seriesNum;
    this.seriesDuration = seriesDuration;
    this.dubName = dubName;
    this.updateTime = updateTime;
    this.videoUri = videoUri;
  }

  public SavePoint(String titleName, int seriesNum, MyDuration seriesDuration, String dubName, String videoUri) {
    this.titleName = titleName;
    this.seriesNum = seriesNum;
    this.seriesDuration = seriesDuration;
    this.dubName = dubName;
    this.videoUri = videoUri;
  }

  public void updateTime(){
    updateTime = new Date();
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

  public MyDuration getSeriesDuration() {
    return seriesDuration;
  }

  public void setSeriesDuration(MyDuration seriesDuration) {
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

  @Override
  public String toString() {
    return "SavePoint{" +
        "titleName='" + titleName + '\'' +
        ", seriesNum=" + seriesNum +
        ", seriesDuration=" + seriesDuration +
        ", dubName='" + dubName + '\'' +
        ", videoUri='" + videoUri + '\'' +
        ", updateTime=" + updateTime +
        '}';
  }
//
//
//  String getTitleName();
//  String getSeries();
//  String getWatchedSeriesTime();
//  String getSeriesTime();

}
