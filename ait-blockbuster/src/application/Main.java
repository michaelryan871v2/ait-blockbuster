package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main
{
  public static void main(String[] args) throws SQLException
  {
    final String url = "jdbc:mysql://localhost:3306/blockbuster?serverTimezone=EST";
    final String username = "root";
    final String password = "password";

    Connection conn = DriverManager.getConnection(url, username, password);
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM movies");

    System.out.println("Movies Available For Rent:\n");
    while (rs.next()) {
      String title = rs.getString("title");
      String director = rs.getString("director");
      String film_duration = rs.getString("film_duration");
      int copies = rs.getInt("copies");
      int available = rs.getInt("available");

      System.out.println(" --> " + title + " by " + director + " is " + film_duration + ". " + "(" + available + " of " + copies + ")");
    }
    
    rs.close();
    stmt.close();
    conn.close();
  }
}