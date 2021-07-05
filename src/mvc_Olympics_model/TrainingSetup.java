package mvc_Olympics_model;

public class TrainingSetup implements StadiumState{
	
	private final int MaxReferees = 2;
	private int numOfReferees = 0;
	private Referee [] arrReferees = new Referee [MaxReferees];
	private int numOfSpectators;
	
	@Override
	public int getNumOfSeats() {
		 return numOfSpectators;
	}
	@Override
	public void setNumOfSeats(int numberOfSeats) {
		 this.numOfSpectators = numberOfSeats/2;
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
