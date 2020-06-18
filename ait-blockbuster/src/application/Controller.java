package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blockbuster.Movie;
import blockbuster.MovieDAO;

public class Controller extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  private MovieDAO dao;
  
  public void init()
  {
    final String url = "jdbc:mysql://localhost:3306/blockbuster?serverTimezone=EST";
    final String username = "root";
    final String password = "password";
    
    dao = new MovieDAO(url, username, password);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    doGet(request, response);
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    final String action = request.getServletPath();
  	
    try {
      switch (action) {
        case "/add":
        case "/edit":
          showEditForm(request, response);
          break;
        case "/insert":
          insertMovie(request, response);
          break;
        case "/update":
          updateMovie(request, response);
          break;
        default:
          viewMovies(request, response);
          break;
      }   
    } catch (SQLException e) {
      throw new ServletException(e);
    }
  }
  
  private void insertMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
		{
		  String title = request.getParameter("title");
		  String director = request.getParameter("director");
		  String filmLength = request.getParameter("filmLength");
		  int copies = Integer.parseInt(request.getParameter("copies").strip());
			
		  dao.insertMovie(title, director, filmLength, copies, copies);
		  response.sendRedirect(request.getContextPath() + "/");
		}
  
  private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	try {
		final int id = Integer.parseInt(request.getParameter("id").strip());
		
		Movie movie = dao.getMovie(id);
		request.setAttribute("movie", movie);
	} finally {
		RequestDispatcher dispatcher = request.getRequestDispatcher("movieform.jsp");
		dispatcher.forward(request, response);
	}
}

  private void updateMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		  final String action = request.getParameter("action") != null
		    ? request.getParameter("action")
		    : request.getParameter("submit").toLowerCase();
		  final int id = Integer.parseInt(request.getParameter("id"));
			
		  Movie movie = dao.getMovie(id);
		  switch (action) {
		    case "rent":
		      movie.rentMe();
		      break;
		    case "return":
		      movie.returnMe();
		      break;
		    case "save":
		      String title = request.getParameter("title");
		      String director = request.getParameter("director");
		      String filmLength = request.getParameter("filmLength");
		      int copies = Integer.parseInt(request.getParameter("copies"));
		      int available = movie.getAvailable() + (copies - movie.getCopies());
				
		      movie.setTitle(title);
		      movie.setFilmLength(filmLength);
		      movie.setDirector(director);
		      movie.setCopies(copies);
		      movie.setAvailable(available);
		      break;
		    case "delete":
		      deleteMovie(id, request, response);
		      return;
		    }

		    dao.updateMovie(movie);
		    response.sendRedirect(request.getContextPath() + "/");
		  }
		    
		private void deleteMovie(final int id, HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException
		{	
		  dao.deleteMovie(dao.getMovie(id));
		  
		  response.sendRedirect(request.getContextPath() + "/");
		}
		
		
  private void viewMovies(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, ServletException, IOException
  {
    List<Movie> movies = dao.getMovies();
    request.setAttribute("movies", movies);

    RequestDispatcher dispatcher = request.getRequestDispatcher("inventory.jsp");
    dispatcher.forward(request, response);
  }
}