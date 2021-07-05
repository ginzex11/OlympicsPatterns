package mvc_Olympice_listener;



public interface OlympiceBLEventsListener {
	public void addCountryToView(String country);
	
	//*
	public void addAddRefereeToView(String name , int achievements);
	
	//*
	public void addStadiumToView(String nameStadium , int numOfSpectators);
	
	public void addCompetitorToView(String competitor , int achievements);
	
	public void addWinnersOfTheCompetitionToView(String firstPlaceInTheOlympics, String secondPlaceInTheOlympics,
			String thirdPlaceInTheOlympics);
	
}