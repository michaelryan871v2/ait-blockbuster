package blockbuster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO
{
  private final String url;
  private final String username;
  private final String password;
  
  public MovieDAO(String url, String username, String password)
  {
    super();
    
    this.url = url;
    this.username = username;
    this.password = password;
  }
  
  public Movie getMovie(int id) throws SQLException
  {
    final String sql = "SELECT * FROM movies WHERE movie_id = ?";
    
    Movie movie = null;
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setInt(1, id);
    ResultSet rs = pstmt.executeQuery();
    
    if (rs.next()) {
      String title = rs.getString("title");
      String director = rs.getString("director");
      String filmLength = rs.getString("filmLength");
      int copies = rs.getInt("copies");
      int available = rs.getInt("available");
      
      movie = new Movie(id, title, director, filmLength, copies, available);
    }
    
    rs.close();
    pstmt.close();
    conn.close();
    
    return movie;
  }
  
  public List<Movie> getMovies() throws SQLException {
    final String sql = "SELECT * FROM movies ORDER BY movie_id ASC";
    
    List<Movie> movies = new ArrayList<Movie>();
    Connection conn = getConnection();
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    
    while (rs.next()) {
      int id = rs.getInt("movie_id");
      String title = rs.getString("title");
      String director = rs.getString("director");
      String filmLength = rs.getString("filmLength");
      int copies = rs.getInt("copies");
      int available = rs.getInt("available");
      
      movies.add(new Movie(id, title, director, filmLength, copies, available));
    }
    
    rs.close();
    stmt.close();
    conn.close();
    
    return movies;
  }
  
  public boolean insertMovie(String title, String director, String filmLength, int copies, int available) throws SQLException
  {
    final String sql = "INSERT INTO movies (title, director, filmLength, copies, available) " +
        "VALUES (?, ?, ?, ?, ?)";
    
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setString(1, title);
    pstmt.setString(2, director);
    pstmt.setString(3, filmLength);
    pstmt.setInt(4, copies);
    pstmt.setInt(5, available);
    int affected = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    return affected == 1;
  }
  
  public boolean updateMovie(Movie movie) throws SQLException
  {
    final String sql = "UPDATE movies SET title = ?, director = ?, filmLength = ?, copies = ?, available = ? " +
        "WHERE movie_id = ?";
    
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
  
    pstmt.setString(1, movie.getTitle());
    pstmt.setString(2, movie.getDirector());
    pstmt.setString(3, movie.getFilmLength());
    pstmt.setInt(4, movie.getCopies());
    pstmt.setInt(5, movie.getAvailable());
    pstmt.setInt(6, movie.getId());
    int affected = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    return affected == 1;
  }
  
  public boolean deleteMovie(Movie movie) throws SQLException
  {
    final String sql = "DELETE FROM movies WHERE movie_id = ?";
    
    Connection conn = getConnection();
    PreparedStatement pstmt = conn.prepareStatement(sql);
    
    pstmt.setInt(1, movie.getId());
    int affected = pstmt.executeUpdate();
    
    pstmt.close();
    conn.close();
    
    return affected == 1;
  }
  
  private Connection getConnection() throws SQLException
  {
    final String driver = "com.mysql.cj.jdbc.Driver";
    
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
    return DriverManager.getConnection(url, username, password);
  }
}