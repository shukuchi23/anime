package org.anime.utils;

import org.anime.config.H2Config;

import java.sql.*;

/**
 * @author Karimov Evgeniy
 * 26.04.2022
 */
public class AppHelper {
  private static final String CREATE_DB =
      "create table if not exists SAVE_POINT(" +
      "title_name varchar(127) primary key," +
      "series_num integer not null default 1," +
      "series_duration varchar(5) not null default '00:00'," +
      "dub_name varchar (15)," +
      "source_uri varchar(255) not null," +
      "update_time timestamp default current_timestamp" +
      ");";

  public void initSavePoint(String dbProp) {
    final String history_db = H2Config.getProperty(dbProp);
    try (final Connection connection = DriverManager.getConnection(history_db)) {
      try (Statement dataQuery = connection.createStatement()) {
        dataQuery.execute(CREATE_DB);
        dataQuery.execute("");
      }

      try (PreparedStatement query = connection.prepareStatement("SELECT * FROM SAVE_POINT");
           ResultSet rs = query.executeQuery()) {

        while (rs.next()) {
          System.out.printf("%s, %s!%n",
              rs.getString(1),
              rs.getString("TARGET"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
