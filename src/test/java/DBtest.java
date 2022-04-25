import config.H2Config;
import org.junit.Test;

import java.sql.*;

public class DBtest {
  private static final String CREATE_QUERY =
      "CREATE TABLE EXAMPLE (GREETING VARCHAR(6), TARGET VARCHAR(6))";
  /**
   * Quaery that populates table with data.
   */
  private static final String DATA_QUERY =
      "INSERT INTO EXAMPLE VALUES('Hello','World')";

  @Test
  public void firstTest(){
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
  }
}
