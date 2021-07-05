package mvc_Olympics_model;

public interface StadiumState {
	public int getNumOfSeats();
	public Referee[] getNumOfReferees();	
	public boolean addReferee(Referee referee);
	public void setNumOfSeats(int numberOfSeats);
}
