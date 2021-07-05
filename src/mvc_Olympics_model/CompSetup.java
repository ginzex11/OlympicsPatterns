package mvc_Olympics_model;

public class CompSetup implements StadiumState{
	
	private final int MaxReferees = 3;
	private int numOfReferees = 0;
	private Referee [] arrReferees = new Referee [MaxReferees];
	private int numOfSpectators;
	
	@Override
	public int getNumOfSeats() {
		 return numOfSpectators;
	}
	@Override
	public void setNumOfSeats(int numberOfSeats) {
		 this.numOfSpectators = numberOfSeats;
	}
	
	@Override
	public Referee[] getNumOfReferees() {
		return arrReferees;
	}
	
	@Override
	public boolean addReferee(Referee referee) {		
		if (numOfReferees < MaxReferees) {
			arrReferees[numOfReferees++] = referee;
			return true;
		}
		return false;
	}
}
