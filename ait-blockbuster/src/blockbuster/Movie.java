package blockbuster;

public class Movie {
	private int id; 
	private String title; 
	private String director; 
	private String filmLength;
	private int copies; 
	private int available; 
	
	public Movie(int id, String title, String director, String filmLength, int copies, int available) {
		super();
		
		this.id = id; 
		this.title = title;
		this.director = director;
		this.filmLength = filmLength;	
		this.copies = copies;
		this.available = available; 
	}
	
	public int getId() {
		return id; 
	}
	
	public String getTitle() {
		return title; 
	}
	
	public String getDirector() {
		return director; 
	}
	
	public String getFilmLength() {
		return filmLength;
	}
	
	public int getCopies() {
		return copies; 
	}
	
	public void setCopies(int copies) {
		this.copies = copies; 
	}
	
	public int getAvailable() {
		return available; 
	}
	
	
	public void rentMe() {
		if (available > 0) {
			available--;
		}
	}
	
	public void returnMe() {
		if (available < copies) {
			available++;
		}
	}
	
	public void setTitle(String title) {
		this.title = title; 
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public void setFilmLength(String filmLength) {
		this.filmLength = filmLength;
	}
	
	public void setAvailable(int available) {
		this.available = available;
	}
}
	
