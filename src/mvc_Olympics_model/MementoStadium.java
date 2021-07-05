package mvc_Olympics_model;

public class MementoStadium {
	private int numberOfSeats;
	private String nameStadium;
	private String location;
	private StadiumState state;

	public MementoStadium (String nameStadium, StadiumState state , String location , int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
		this.nameStadium = nameStadium;
		this.location = location;
		this.state = state;
	}
	
	public StadiumState getState() {
		return state;
	}
	
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	
	public String getNameStadium() {
		return nameStadium;
	}
	public String getLocation() {
		return location;
	}
	
	public void setState(StadiumState state) {
		this.state = state;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setNameStadium(String nameStadium) {
		this.nameStadium = nameStadium;
	}
	public void setNumberOfSeatsn(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
}
